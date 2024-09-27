package de.janhopp.luebeckmensawidget.widget

import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage

data class MensaWidgetConfig(
    val showDate: Boolean = Option.ShowDate.defaultValue,
    val useEmoji: Boolean = Option.UseEmoji.defaultValue,
    val priceGroup: PriceGroup = PriceGroup.valueOf(Option.PriceGroup.defaultValue),
    val filterDeals: Boolean = Option.FilterDeals.defaultValue,
    val locations: Set<Location> = Option.Locations.defaultValue.mapNotNull { Location.fromCode(it) }.toSet()
)

suspend fun OptionsStorage.getWidgetConfig() = MensaWidgetConfig(
    getBoolean(Option.ShowDate),
    getBoolean(Option.UseEmoji),
    PriceGroup.valueOf(getString(Option.PriceGroup)),
    getBoolean(Option.FilterDeals),
    getStringSet(Option.Locations).mapNotNull { Location.fromCode(it) }.toSet()
)
