package de.janhopp.luebeckmensawidget.widget

import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.Allergens
import de.janhopp.luebeckmensawidget.api.model.City
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.api.model.DietFilter

data class MensaWidgetConfig(
    val showDate: Boolean = Option.ShowDate.defaultValue,
    val useEmoji: Boolean = Option.UseEmoji.defaultValue,
    val priceGroup: PriceGroup = PriceGroup.valueOf(Option.PriceGroup.defaultValue),
    val filterDeals: Boolean = Option.FilterDeals.defaultValue,
    val city: City = City.valueOf(Option.MensaCity.defaultValue),
    val locations: Set<Location> = Location.fromStringSet(Option.Locations.defaultValue),
    val allergens: Set<Allergens> = Allergens.fromStringSet(Option.Allergens.defaultValue),
    val dietFilter: DietFilter = DietFilter.fromName(Option.DietFilter.defaultValue),
)

suspend fun OptionsStorage.getWidgetConfig() = MensaWidgetConfig(
    getBoolean(Option.ShowDate),
    getBoolean(Option.UseEmoji),
    PriceGroup.valueOf(getString(Option.PriceGroup)),
    getBoolean(Option.FilterDeals),
    City.valueOf(getString(Option.MensaCity)),
    getStringSet(Option.Locations).mapNotNull { Location.fromCode(it) }.toSet(),
    Allergens.fromStringSet(getStringSet(Option.Allergens)),
    DietFilter.fromName(getString(Option.DietFilter)),
)
