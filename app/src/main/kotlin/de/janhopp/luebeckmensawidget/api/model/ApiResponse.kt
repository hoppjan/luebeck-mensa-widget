package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("data") val meals: List<Meal>,
)

fun ApiResponse.toMensaDays(): List<MensaDay> =
    meals.groupBy { it.date }.map { (date, meals) -> MensaDay(date, meals) }
