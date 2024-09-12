package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WidgetConfigurationScreen(
    onConfigurationFinished: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Text(
            text = "Widget Configuration Screen",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Button(
            onClick = onConfigurationFinished,
        ) {
            Text(text = "Finish Configuration")
        }
    }
}

@Composable
@Preview
fun Preview_WidgetConfigurationScreen() {
    WidgetConfigurationScreen(
        onConfigurationFinished = {},
    )
}
