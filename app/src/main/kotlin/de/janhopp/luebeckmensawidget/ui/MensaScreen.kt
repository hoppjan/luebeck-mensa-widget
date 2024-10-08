package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.theme.glanceString
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MensaScreen(
    day: MensaDay?,
    update: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val options = OptionsStorage(LocalContext.current)
    var widgetConfig by remember { mutableStateOf(MensaWidgetConfig()) }
    var isUpdating by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isUpdating = true
        widgetConfig = options.getWidgetConfig()
        isUpdating = false
    }

    Box(
        modifier = GlanceModifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = if (widgetConfig.showDate) Alignment.TopEnd else Alignment.BottomEnd,
    ) {
        if (isUpdating)
            LoadingView()
        else if (day != null)
            MensaDayView(day, widgetConfig)
        else
            ErrorView(errorMessage = glanceString(R.string.error_could_not_load_menu))

        RefreshButton {
            scope.launch {
                isUpdating = true
                update()
                delay(timeMillis = 2000) // needed for visible feedback for user
                isUpdating = false
            }
        }
    }
}
