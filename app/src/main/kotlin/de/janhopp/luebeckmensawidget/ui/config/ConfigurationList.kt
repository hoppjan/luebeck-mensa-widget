package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.Allergens
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.api.model.toStringSet
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme
import de.janhopp.luebeckmensawidget.ui.utils.stringRes
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ConfigurationList(
    modifier: Modifier = Modifier,
) {
    val options = OptionsStorage(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    var widgetConfig by remember { mutableStateOf(MensaWidgetConfig()) }
    val onSelectedLocationsChanged: (Set<Location>) -> Unit = { selected ->
        widgetConfig = widgetConfig.copy(locations = selected)
        coroutineScope.launch {
            options.setStringSet(Option.Locations, selected.toStringSet())
        }
    }
    val onSelectedAllergensChanged: (Set<Allergens>) -> Unit = { selected ->
        widgetConfig = widgetConfig.copy(allergens = selected)
        coroutineScope.launch {
            options.setStringSet(Option.Allergens, selected.toStringSet())
        }
    }
    LaunchedEffect(Unit) {
        widgetConfig = options.getWidgetConfig()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .then(modifier),
    ) {
        Spacer(modifier = Modifier.height(height = 4.dp))
        SectionLabel(text = stringResource(R.string.settings_section_widget))
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
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
        SectionLabel(text = stringResource(R.string.settings_section_canteen))
        ListItem(
            headlineContent = {
                Text(text = stringResource(R.string.option_price_group))
            },
            supportingContent = {
                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    PriceGroup.entries.forEachIndexed { index, priceGroup ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults
                                .itemShape(index = index, count = PriceGroup.entries.size),
                            selected = widgetConfig.priceGroup == priceGroup,
                            onClick = {
                                widgetConfig = widgetConfig.copy(priceGroup = priceGroup)
                                coroutineScope.launch {
                                    options.setString(Option.PriceGroup, priceGroup.name)
                                }
                            }
                        ) {
                            Text(text = priceGroup.stringRes())
                        }
                    }
                }
            }
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
        ListItem(
            headlineContent = {
                Text(
                    text = stringResource(R.string.option_location),
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            },
            supportingContent = {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Location.entries.forEach { item ->
                        SelectableChip<Location>(
                            item = item,
                            selected = item in widgetConfig.locations,
                            itemToText = { location -> location.stringRes() },
                            onSelectionChanged = { location, wasSelected ->
                                widgetConfig = widgetConfig.copy(
                                    locations = widgetConfig.locations
                                        .let { locations ->
                                            if (wasSelected) locations + location
                                            else locations.filterNot { it == location }
                                        }
                                        .toSet()
                                )
                                onSelectedLocationsChanged(widgetConfig.locations)
                            },
                        )
                    }
                }
            }
        )
        ListItem(
            headlineContent = {
                Text(
                    text = stringResource(R.string.option_allergens),
                    modifier = Modifier.padding(bottom = 20.dp),
                )
            },
            supportingContent = {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Allergens.allAllergens.forEach { item ->
                        SelectableChip<Allergens>(
                            item = item,
                            selected = (item in widgetConfig.allergens),
                            itemToText = { stringResource(it.resId) },
                            onSelectionChanged = { allergen, wasSelected ->
                                widgetConfig = widgetConfig.copy(
                                    allergens = widgetConfig.allergens
                                        .let { allergens ->
                                            if (wasSelected) allergens + allergen
                                            else allergens.filterNot { it == allergen }
                                        }
                                        .toSet()
                                )
                                onSelectedAllergensChanged(widgetConfig.allergens)
                            },
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Preview
@Composable
fun Preview_ConfigurationList() = MensaTheme {
    ConfigurationList()
}
