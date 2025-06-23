package de.janhopp.luebeckmensawidget.api.model

enum class Location(
    val code: String,
) {
    MensaLuebeck(code = "HL_ME"),
    CafeteriaLuebeck(code = "HL_CA"),
    MusikhochschuleLuebeck(code = "HL_MH"),
    BitsAndBytes(code = "HL_BB")
    ;

    companion object {
        fun fromCode(code: String): Location? = entries.find { it.code == code }

        fun fromStringSet(strings: Set<String>): Set<Location> = strings
            .mapNotNull { code ->
                Location.entries.find { location -> location.code == code }
            }
            .toSet()
    }
}

fun Set<Location>.toCodes() = joinToString(separator = ",") { it.code }

fun Iterable<Location>.toStringSet(): Set<String> = map { it.code }.toSet()
