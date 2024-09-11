package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
data class MensaLocation(
    val code: String,
    val name: String,
    val city: String,
)
