package de.janhopp.luebeckmensawidget.widget

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.theme.AppTheme
import de.janhopp.luebeckmensawidget.ui.MensaScreen
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaDay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MensaWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val api = MensaApi()
        val storage = MenuStorage(context)

        provideContent {
            var config by remember { mutableStateOf<MensaWidgetConfig?>(null) }
            var todayFromApi by remember { mutableStateOf<MensaDay?>(null) }
            var todayFromStorage by remember { mutableStateOf<MensaDay?>(null) }

            LaunchedEffect(Unit) {
                config = OptionsStorage(context).getWidgetConfig()
                todayFromApi = api.getMealsToday(config!!.locations)
                    ?.also { storage.setMensaDays(listOf(it))  }
                todayFromStorage = storage.getMensaDay(currentTime.mensaDay).firstOrNull()
            }
            val scope = rememberCoroutineScope()
            val update: () -> Unit = { scope.launch { update(context, id) } }
            val today = when {
                todayFromApi != null -> todayFromApi
                todayFromStorage != null -> todayFromStorage
                else -> null
            }
            AppTheme {
                MensaScreen(today, update)
            }
        }
    }
}
