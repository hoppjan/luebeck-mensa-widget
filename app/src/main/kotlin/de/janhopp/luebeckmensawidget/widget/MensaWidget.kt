package de.janhopp.luebeckmensawidget.widget

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import de.janhopp.luebeckmensawidget.SyncWorker.Companion.enqueueSyncWork
import de.janhopp.luebeckmensawidget.theme.AppTheme
import de.janhopp.luebeckmensawidget.ui.MensaScreen
import de.janhopp.luebeckmensawidget.utils.syncToday
import kotlinx.coroutines.launch

class MensaWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        context.enqueueSyncWork()

        provideContent {
            val scope = rememberCoroutineScope()
            val update: () -> Unit = {
                scope.launch {
                    context.syncToday()
                    updateAll(context)
                }
            }
            val state = currentState<MensaWidgetState>()
            val today = state.mensaDay
            AppTheme {
                MensaScreen(today, update)
            }
        }
    }

    override val stateDefinition: GlanceStateDefinition<MensaWidgetState>
        get() = MensaWidgetStateDefinition()

    companion object {
        suspend fun updateAll(context: Context) = MensaWidget().updateAll(context)
    }
}
