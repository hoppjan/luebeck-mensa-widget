package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme
import kotlinx.coroutines.launch

@Composable
fun ConfigurationList(
    modifier: Modifier = Modifier,
) {
    val options = OptionsStorage(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    var isShowDateEnabled by remember { mutableStateOf(Option.ShowDate.defaultValue) }
    var isUseEmojiEnabled by remember { mutableStateOf(Option.UseEmoji.defaultValue) }
    var priceGroupSelected by remember { mutableStateOf(Option.PriceGroup.defaultValue) }

    LaunchedEffect(Unit) {
        isShowDateEnabled = options.get(Option.ShowDate)
        isUseEmojiEnabled = options.get(Option.UseEmoji)
        priceGroupSelected = options.getString(Option.PriceGroup)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        OptionSwitch(
            text = "Show date",
            checked = isShowDateEnabled,
            onCheckedChange = {
                isShowDateEnabled = !isShowDateEnabled
                coroutineScope.launch {
                    options.set(Option.ShowDate, isShowDateEnabled)
                }
            },
        )
        OptionSwitch(
            text = "Use emoji in meal names",
            checked = isUseEmojiEnabled,
            onCheckedChange = {
                isUseEmojiEnabled = !isUseEmojiEnabled
                coroutineScope.launch {
                    options.set(Option.UseEmoji, isUseEmojiEnabled)
                }
            },
        )
        OptionDropdownMenu(
            text = "Price to show",
            options = PriceGroup.names,
            onOptionSelected = {
                priceGroupSelected = it
                coroutineScope.launch {
                    options.setString(Option.PriceGroup, it)
                }
            },
            selectedOption = priceGroupSelected,
        )
    }
}

@Preview
@Composable
fun Preview_ConfigurationList() = MensaTheme {
    ConfigurationList()
}
