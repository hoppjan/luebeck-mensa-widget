package de.janhopp.luebeckmensawidget.utils

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.*

// this is German... @importantus & @draculente
fun DayOfWeek.toMensaApiFormat() = when (this) {
    MONDAY -> "mo"
    TUESDAY -> "di"
    WEDNESDAY -> "mi"
    THURSDAY -> "do"
    FRIDAY -> "fr"
    else -> "mo" // no service at weekends
    /*SATURDAY -> "sa"
    SUNDAY -> "so"*/
}
