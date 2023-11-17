package de.janhopp.luebeckmensawidget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import de.janhopp.luebeckmensawidget.ui.components.StyledText

@Composable
fun ErrorView(
    errorMessage: String,
) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        StyledText(text = errorMessage)
    }
}
