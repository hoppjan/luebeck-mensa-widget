package de.janhopp.luebeckmensawidget.api.model

enum class Location(
    val code: String,
) {
    MensaLuebeck(code = "HL_ME"),
    CafeteriaLuebeck(code = "HL_CA"),
    MusikhochschuleLuebeck(code = "HL_MH"),
    ;

    companion object {
        fun fromCode(code: String): Location? = entries.find { it.code == code }
    }
}

fun Set<Location>.toCodes() = joinToString(separator = ",") { it.code }

fun Iterable<Location>.toStringSet(): Set<String> = map { it.code }.toSet()
