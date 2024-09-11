package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("data") val meals: List<ApiMeal>,
)

@Serializable
data class ApiMeal(
    val name: String,
    val date: String,
    val price: GroupedPrices,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val location: ApiMensaLocation,
    val allergens: List<Allergen>,
)

fun ApiMeal.toMeal() = Meal(name, date, price, vegan, vegetarian, allergens)

@Serializable
data class ApiMensaLocation(
    val code: String,
    val name: String,
    val city: String,
)

fun ApiResponse.toMensaDays(): List<MensaDay> =
    meals.groupBy { it.date }
        .map { (dateKey, meals) ->
            MensaDay(date = dateKey, open = true, meals = meals.map { m -> m.toMeal() })
        }
