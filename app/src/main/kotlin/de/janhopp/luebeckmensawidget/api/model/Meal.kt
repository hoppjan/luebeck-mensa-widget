package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Meal(
    val name: String,
    val date: String,
    val price: GroupedPrices,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val location: MensaLocation,
    val allergens: List<Allergen>,
    val language: Language = Language.German,
) {
    val widgetName = when {
        vegan -> "🌻 "
        vegetarian -> "🌽 "
        "Fi" in allergens.map { it.code } -> "🐟 "
        else -> "🥩 "
    }.plus(name.replace(Regex("[A-Z ]+: "), ""))
}
