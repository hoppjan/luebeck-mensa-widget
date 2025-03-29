package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Language(
    val code: String,
    val name: String,
) {
    companion object {
        val German = Language("de", "Deutsch")
        val English = Language("en", "English")
    }
}
