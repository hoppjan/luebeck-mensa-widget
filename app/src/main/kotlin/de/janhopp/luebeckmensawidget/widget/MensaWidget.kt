package de.janhopp.luebeckmensawidget.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.theme.AppTheme
import de.janhopp.luebeckmensawidget.ui.MensaScreen
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MensaWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val api = MensaApi()
        val storage = MenuStorage(context)
        val config = OptionsStorage(context).getWidgetConfig()
        withContext(Dispatchers.IO) {
            val meals = api.getMealsToday(config.locations)

            val today = if (meals.isEmpty()) {
                storage.getMensaDay(currentTime.mensaDay).first()
            } else {
                storage.setMensaDays(meals)
                meals.first()
            }

            val update: () -> Unit = { launch { update(context, id) } }

            provideContent {
                AppTheme {
                    MensaScreen(today, update)
                }
            }
        }
    }
}
