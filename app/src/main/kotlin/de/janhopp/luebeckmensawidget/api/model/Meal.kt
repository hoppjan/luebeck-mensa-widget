package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val name: String,
    val price: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val location: MensaLocation,
    val allergens: List<Allergen>,
    val priceByGroup: Price
) {
    val widgetName = when {
        vegan -> "🌻 "
        vegetarian -> "🌽 "
        else -> "🥩 "
    }.plus(name)

    val studentPrice = String.format("%.2f€", priceByGroup.students)
}
