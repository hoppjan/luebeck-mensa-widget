package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme
import de.janhopp.luebeckmensawidget.utils.Icons

@Composable
inline fun <reified T> SelectableChip(
    item: T,
    selected: Boolean,
    crossinline itemToText: @Composable (T) -> String = { it.toString() },
    crossinline onSelectionChanged: (T, Boolean) -> Unit = { _, _ -> },
) {
    ElevatedFilterChip(
        onClick = { onSelectionChanged(item, !selected) },
        label = {
            Text(text = itemToText(item))
        },
        selected = selected,
        trailingIcon = if (selected) {
            {
                Icon(
                    painter = Icons.paintClear(),
                    contentDescription = "Clear icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else null
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_SelectableChip() = MensaTheme {
    Row(
        modifier = Modifier.fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp)
    ) {
        SelectableChip(item = "Filter chip", selected = true)
        Spacer(modifier = Modifier.size(4.dp))
        SelectableChip(item = "Filter chip", selected = false)
        Spacer(modifier = Modifier.size(4.dp))
        SelectableChip(item = "Filter chip", selected = true)
        Spacer(modifier = Modifier.size(4.dp))
        SelectableChip(item = "Filter chip", selected = false)
    }
}
