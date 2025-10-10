package de.janhopp.luebeckmensawidget.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified T: Enum<T>> ColumnScope.OptionDropdownMenu(
    text: String,
    options: List<T>,
    selectedOption: T,
    crossinline optionToString: @Composable (T) -> String,
    crossinline onOptionSelected: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = { Text(text) },
        trailingContent = {
            ExposedDropdownMenuBox(
                modifier = Modifier.align(Alignment.End),
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .menuAnchor(
                            type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                            enabled = true,
                        ),
                    readOnly = true,
                    value = optionToString(selectedOption),
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(optionToString(option)) },
                            onClick = {
                                expanded = false
                                onOptionSelected(option)
                            }
                        )
                    }
                }
            }
        }
    )
}
