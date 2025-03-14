package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Language(
    val code: String,
    val name: String,
) {
    @Serializable
    data object German : Language("de", "Deutsch")
    @Serializable
    data object English : Language("en", "English")
}
