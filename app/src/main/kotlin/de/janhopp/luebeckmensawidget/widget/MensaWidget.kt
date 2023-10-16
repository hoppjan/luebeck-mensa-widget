package de.janhopp.luebeckmensawidget.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.theme.AppTheme
import de.janhopp.luebeckmensawidget.ui.MensaScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MensaWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val api = MensaApi()
        withContext(Dispatchers.IO) {
            val meals = api.getMealsToday()

            provideContent {
                AppTheme {
                    MensaScreen(meals)
                }
            }
        }
    }
}
