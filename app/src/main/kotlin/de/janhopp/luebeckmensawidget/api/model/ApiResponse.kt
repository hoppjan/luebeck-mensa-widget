package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import de.janhopp.luebeckmensawidget.api.MensaJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class ApiResponse(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("data") val meals: List<Meal>,
)

fun String.toApiResponse() = MensaJson.decodeFromString<ApiResponse>(this)

fun ApiResponse.toMensaDays(): List<MensaDay> =
    meals.groupBy { it.date }.map { (date, meals) -> MensaDay(date, meals.distinct()) }

fun List<MensaDay>.filterByDate(date: String): MensaDay =
    firstOrNull { it.date == date } ?: MensaDay(date, emptyList())
