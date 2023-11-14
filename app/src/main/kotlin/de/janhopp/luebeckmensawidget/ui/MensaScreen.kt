package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.ui.components.StyledText

@Composable
fun MensaScreen(
    day: MensaDay?,
    update: () -> Unit,
) {
    Box(
        modifier = GlanceModifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        if (day != null)
            MensaDayView(day)
        else
            StyledText(
                modifier = GlanceModifier
                    .fillMaxSize() // goes to box-alignment if this is not set
                    .padding(all = 8.dp),
                text = "Couldn't load menu..."
            )

        RefreshButton(update)
    }
}
