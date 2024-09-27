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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import de.janhopp.luebeckmensawidget.ui.utils.stringRes
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
            text = stringResource(R.string.option_show_date),
            checked = widgetConfig.showDate,
            onCheckedChange = {
                widgetConfig = widgetConfig.copy(showDate = it)
                coroutineScope.launch {
                    options.setBoolean(Option.ShowDate, it)
                }
            },
        )
        OptionSwitch(
            text = stringResource(R.string.option_use_emoji),
            checked = widgetConfig.useEmoji,
            onCheckedChange = {
                widgetConfig = widgetConfig.copy(useEmoji = it)
                coroutineScope.launch {
                    options.setBoolean(Option.UseEmoji, it)
                }
            },
        )
        OptionDropdownMenu<PriceGroup>(
            text = stringResource(R.string.option_price_group),
            options = PriceGroup.entries,
            optionToString = { group -> group.stringRes() },
            onOptionSelected = { group ->
                widgetConfig = widgetConfig.copy(priceGroup = group)
                coroutineScope.launch {
                    options.setString(Option.PriceGroup, group.name)
                }
            },
            selectedOption = widgetConfig.priceGroup,
        )
        OptionSwitch(
            text = stringResource(R.string.option_filter_deals),
            checked = widgetConfig.filterDeals,
            onCheckedChange = {
                widgetConfig = widgetConfig.copy(filterDeals = it)
                coroutineScope.launch {
                    options.setBoolean(Option.FilterDeals, it)
                }
            },
        )
        OptionDropdownMenu<Location>(
            text = stringResource(R.string.option_location),
            options = Location.entries,
            optionToString = { location -> location.stringRes() },
            onOptionSelected = { locations ->
                widgetConfig = widgetConfig.copy(locations = setOf(locations))
                coroutineScope.launch {
                    options.setStringSet(Option.Locations, setOf(locations.code))
                }
            },
            selectedOption = widgetConfig.locations.first(),
        )
    }
}

@Preview
@Composable
fun Preview_ConfigurationList() = MensaTheme {
    ConfigurationList()
}
