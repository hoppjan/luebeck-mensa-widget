package de.janhopp.luebeckmensawidget.utils

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.*

// this is German... @importantus & @draculente
fun DayOfWeek.toMensaApiFormat() = when (this) {
    MONDAY -> "mo&week=current"
    TUESDAY -> "di&week=current"
    WEDNESDAY -> "mi&week=current"
    THURSDAY -> "do&week=current"
    FRIDAY -> "fr&week=current"
    else -> "mo&week=next" // no service at weekends
    /*SATURDAY -> "sa"
    SUNDAY -> "so"*/
}
