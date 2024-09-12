package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme

@Composable
fun OptionSwitch(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(text = text)
        },
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    )
}

@Preview
@Composable
fun Preview_OptionSwitch() = MensaTheme {
    Column {
        OptionSwitch(
            text = "Enabled option",
            checked = true,
            onCheckedChange = {},
        )
        OptionSwitch(
            text = "Disabled option",
            checked = false,
            onCheckedChange = {},
        )
    }
}
