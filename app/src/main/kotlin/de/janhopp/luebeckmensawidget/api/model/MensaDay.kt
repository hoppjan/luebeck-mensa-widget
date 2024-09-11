package de.janhopp.luebeckmensawidget.api.model

import de.janhopp.luebeckmensawidget.api.MensaJson
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

@Serializable
data class MensaDay(
    val date: String,
    val open: Boolean,
    val meals: List<Meal>,
) {
    val localDate: LocalDate = date.split("T").first().let(LocalDate::parse)

    fun toJson(): String = MensaJson.encodeToString(value = this)

    companion object {
        fun fromJsonOrNull(json: String?): MensaDay? =
            json?.let { MensaJson.decodeFromString(string = it) }
    }
}
