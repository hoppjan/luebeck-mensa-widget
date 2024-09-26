package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.theme.toGlance
import de.janhopp.luebeckmensawidget.ui.components.StyledText
import de.janhopp.luebeckmensawidget.utils.toDisplayString

@Composable
fun DateTopBar(day: MensaDay) {
    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .padding(start = 2.dp),
    ) {
        StyledText(
            text = day.toDisplayString(),
            style = LocalTextStyle.current.toGlance()
                .copy(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
    }
}
