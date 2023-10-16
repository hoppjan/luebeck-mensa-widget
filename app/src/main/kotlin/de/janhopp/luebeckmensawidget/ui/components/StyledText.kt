package de.janhopp.luebeckmensawidget.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import de.janhopp.luebeckmensawidget.theme.toGlance

@Composable
fun StyledText(
    modifier: GlanceModifier = GlanceModifier,
    style: TextStyle = LocalTextStyle.current.toGlance(),
    text: String,
) {
    Text(
        modifier = modifier,
        style = style,
        text = text,
    )
}
