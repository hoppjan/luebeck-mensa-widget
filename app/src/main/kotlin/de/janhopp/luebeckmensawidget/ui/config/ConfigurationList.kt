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
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlinx.coroutines.launch

@Composable
fun ConfigurationList(
    modifier: Modifier = Modifier,
) {
    val options = OptionsStorage(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    var widgetConfig by remember { mutableStateOf(MensaWidgetConfig()) }
    LaunchedEffect(Unit) {
        widgetConfig = options.getWidgetConfig()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        OptionSwitch(
            text = "Show date",
            checked = widgetConfig.showDate,
            onCheckedChange = {
                widgetConfig = widgetConfig.copy(showDate = it)
                coroutineScope.launch {
                    options.setBoolean(Option.ShowDate, it)
                }
            },
        )
        OptionSwitch(
            text = "Use emoji in meal names",
            checked = widgetConfig.useEmoji,
            onCheckedChange = {
                widgetConfig = widgetConfig.copy(useEmoji = it)
                coroutineScope.launch {
                    options.setBoolean(Option.UseEmoji, it)
                }
            },
        )
        OptionDropdownMenu(
            text = "Price to show",
            options = PriceGroup.names,
            onOptionSelected = {
                widgetConfig = widgetConfig.copy(priceGroup = PriceGroup.valueOf(it))
                coroutineScope.launch {
                    options.setString(Option.PriceGroup, it)
                }
            },
            selectedOption = widgetConfig.priceGroup.name,
        )
        OptionSwitch(
            text = "Filter deals",
            checked = widgetConfig.filterDeals,
            onCheckedChange = {
                widgetConfig = widgetConfig.copy(filterDeals = it)
                coroutineScope.launch {
                    options.setBoolean(Option.FilterDeals, it)
                }
            },
        )
    }
}

@Preview
@Composable
fun Preview_ConfigurationList() = MensaTheme {
    ConfigurationList()
}
