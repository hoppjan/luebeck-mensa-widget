package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SectionLabel(
    text: String,
    modifier: Modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier,
    )
}
