package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.ui.components.StyledText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MensaScreen(
    day: MensaDay?,
    update: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var isUpdating by remember { mutableStateOf(false) }

    Box(
        modifier = GlanceModifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        if (isUpdating)
            LoadingView()
        else if (day != null)
            MensaDayView(day)
        else
            StyledText(
                modifier = GlanceModifier
                    .fillMaxSize() // goes to box-alignment if this is not set
                    .padding(all = 8.dp),
                text = "Couldn't load menu..."
            )

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
