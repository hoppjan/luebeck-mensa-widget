package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val name: String,
    val date: String,
    val price: GroupedPrices,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val location: MensaLocation,
    val allergens: List<Allergen>,
) {
    val widgetName = when {
        vegan -> "🌻 "
        vegetarian -> "🌽 "
        "Fi" in allergens.map { it.code } -> "🐟 "
        else -> "🥩 "
    }.plus(name)
}
