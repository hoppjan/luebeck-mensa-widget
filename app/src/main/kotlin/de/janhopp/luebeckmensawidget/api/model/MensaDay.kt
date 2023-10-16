package de.janhopp.luebeckmensawidget.api.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class MensaDay(
    val date: String,
    val week: MensaWeek,
    val open: Boolean,
    val meals: List<Meal>,
) {
    val localDate: LocalDate = date.split("T").first().let(LocalDate::parse)
}
