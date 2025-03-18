package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.unit.ColorProvider
import de.janhopp.luebeckmensawidget.ui.activity.MensaDayActivity

@Composable
fun LoadingView() {
    Box(
        modifier = GlanceModifier.fillMaxSize()
            .clickable(actionStartActivity<MensaDayActivity>()),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = ColorProvider(MaterialTheme.colorScheme.primary)
        )
    }
}
