package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme

@Composable
fun ConfigurationList(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        var isShowDateEnabled by remember { mutableStateOf(false) }

        SwitchOption(
            text = "Show date",
            checked = isShowDateEnabled,
            onCheckedChange = { isShowDateEnabled = it },
        )
    }
}

@Preview
@Composable
fun Preview_ConfigurationList() = MensaTheme {
    ConfigurationList()
}
