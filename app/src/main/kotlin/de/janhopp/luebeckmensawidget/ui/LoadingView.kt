package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.unit.ColorProvider

@Composable
fun LoadingView() {
    Box(
        modifier = GlanceModifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = ColorProvider(MaterialTheme.colorScheme.primary)
        )
    }
}
