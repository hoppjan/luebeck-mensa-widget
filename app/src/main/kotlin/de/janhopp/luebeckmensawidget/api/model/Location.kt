package de.janhopp.luebeckmensawidget.api.model

enum class Location(
    val code: String,
) {
    MensaLuebeck(code = "HL_ME"),
    CafeteriaLuebeck(code = "HL_CA"),
    MusikhochschuleLuebeck(code = "HL_MH"),
    Mensa1Kiel(code = "KI_ME1"),
    Cafeteria1Kiel(code = "KI_CA1"),
    Mensa2Kiel(code = "KI_ME2"),
    Cafeteria2Kiel(code = "KI_CA2"),
    KesselhausKiel(code = "KI_KESSELHAUS"),
    SchwentineMensaKiel(code = "KI_SCHWENTINE"),
    AmericanDinerKiel(code = "KI_DINER"),
    MensaDocksideKiel(code = "KI_DOCKSIDE"),
    MensaHeide(code = "HEI_ME1"),
    MensaFlensburg(code = "FL_ME1"),
    CafeteriaAFlensburg(code = "FL_CA1"),
    CafeteriaBFlensburg(code = "FL_CA2"),
    MensaOsterroenfeld(code = "RD_ME1"),
    CafeteriaWedel(code = "PI_CA1"),
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
