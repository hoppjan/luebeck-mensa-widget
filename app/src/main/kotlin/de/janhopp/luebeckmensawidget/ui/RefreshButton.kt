package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.padding
import androidx.glance.unit.ColorProvider
import de.janhopp.luebeckmensawidget.R

@Composable
fun RefreshButton(
    update: () -> Unit,
) {
    Image(
        modifier = GlanceModifier
            .padding(all = 8.dp)
            .cornerRadius(radius = 16.dp)
            .clickable(update),
        provider = ImageProvider(R.drawable.refresh),
        colorFilter = MaterialTheme.colorScheme.onBackground.toColorFilter(),
        contentDescription = null
    )
}

private fun Color.toColorFilter() =
    ColorFilter.tint(ColorProvider(color = this))
