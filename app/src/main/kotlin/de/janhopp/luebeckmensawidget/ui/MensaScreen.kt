package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Box
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.ui.components.StyledText

@Composable
fun MensaScreen(day: MensaDay?) {
    Box(
        modifier = GlanceModifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        if (day != null)
            MensaDayView(day)
        else
            StyledText(text = "Couldn't load menu...")
    }
}
