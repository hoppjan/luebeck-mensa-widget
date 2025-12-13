package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import de.janhopp.luebeckmensawidget.api.MensaJson
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

@Serializable
@Keep
data class MensaDay(
    val date: String,
    val meals: List<Meal>,
) {
    val localDate: LocalDate = date.split("T").first().let(LocalDate::parse)

    fun toJson(): String = MensaJson.encodeToString(value = this)

    companion object {
        fun fromJsonOrNull(json: String?): MensaDay? =
            json?.let { MensaJson.decodeFromString(string = it) }
    }
}

fun List<Meal>.filterDeals(isEnabled: Boolean): List<Meal> =
    if (isEnabled)
        filterNot { meal ->
            listOf("!", "€").any { it in meal.name }
        }
    else this

fun List<Meal>.filterByDiet(dietFilter: DietFilter): List<Meal> =
    when (dietFilter) {
        DietFilter.None -> this
        DietFilter.Vegetarian -> filter { meal -> meal.vegetarian || meal.vegan }
        DietFilter.Vegan -> filter { meal -> meal.vegan }
    }
