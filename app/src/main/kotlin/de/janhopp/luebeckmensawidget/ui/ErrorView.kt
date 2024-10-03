package de.janhopp.luebeckmensawidget.ui

import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.TextAlign
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.theme.toColorFilter
import de.janhopp.luebeckmensawidget.theme.toGlance
import de.janhopp.luebeckmensawidget.ui.components.StyledText

@Composable
fun ErrorView(
    @DrawableRes imageRes: Int = R.drawable.error,
    errorMessage: String,
) {
    Box(
        modifier = GlanceModifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = GlanceModifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Image(
                    modifier = GlanceModifier
                        .padding(all = 8.dp)
                        .size(64.dp),
                    provider = ImageProvider(imageRes),
                    contentDescription = errorMessage,
                    colorFilter = MaterialTheme.colorScheme.onBackground.toColorFilter(),
                )
            }
            item {
                StyledText(
                    text = errorMessage,
                    modifier = GlanceModifier.padding(all = 8.dp),
                    style = LocalTextStyle.current.toGlance()
                        .copy(textAlign = TextAlign.Center),
                )
            }
        }
    }
}
