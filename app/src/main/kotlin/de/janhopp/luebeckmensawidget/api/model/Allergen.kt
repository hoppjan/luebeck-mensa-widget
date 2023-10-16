package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Allergen(
    val code: String,
    val name: String,
)
