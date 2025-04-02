package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class MensaLocation(
    val code: String,
    val name: String,
    val city: String,
)
