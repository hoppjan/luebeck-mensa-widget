package de.janhopp.luebeckmensawidget.widget

import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage

data class MensaWidgetConfig(
    var showDate: Boolean = Option.ShowDate.defaultValue,
    var useEmoji: Boolean = Option.UseEmoji.defaultValue,
    var priceGroup: PriceGroup = PriceGroup.valueOf(Option.PriceGroup.defaultValue),
    var filterDeals: Boolean = Option.FilterDeals.defaultValue,
)

suspend fun OptionsStorage.getWidgetConfig() = MensaWidgetConfig(
    getBoolean(Option.ShowDate),
    getBoolean(Option.UseEmoji),
    PriceGroup.valueOf(getString(Option.PriceGroup)),
    getBoolean(Option.FilterDeals),
)
