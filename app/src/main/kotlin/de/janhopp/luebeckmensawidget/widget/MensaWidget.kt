package de.janhopp.luebeckmensawidget.widget

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
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

            suspend fun updateConfigAndMenu() {
                config = OptionsStorage(context).getWidgetConfig()
                todayFromApi = api.getMealsToday(config!!.locations)
                    ?.also { storage.setMensaDays(listOf(it)) }
                todayFromStorage = storage.getMensaDay(currentTime.mensaDay).firstOrNull()
            }
            LaunchedEffect(Unit) {
                updateConfigAndMenu()
            }
            val scope = rememberCoroutineScope()
            val update: () -> Unit = {
                scope.launch {
                    updateConfigAndMenu()
                    updateAll(context)
                }
            }
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
