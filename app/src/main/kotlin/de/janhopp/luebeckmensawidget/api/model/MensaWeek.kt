package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MensaWeek {
    @SerialName("current") Current,
    @SerialName("next") Next
}
