package de.janhopp.luebeckmensawidget.utils

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.*

fun DayOfWeek.toMensaApiFormat() = when (this) {
    MONDAY -> "mon&week=current"
    TUESDAY -> "tue&week=current"
    WEDNESDAY -> "wed&week=current"
    THURSDAY -> "thu&week=current"
    FRIDAY -> "fri&week=current"
    else -> "mon&week=next" // no service at weekends
    /*SATURDAY -> "sat"
    SUNDAY -> "sun"*/
}
