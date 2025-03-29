package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Allergen(
    val code: String,
    val name: String,
)
