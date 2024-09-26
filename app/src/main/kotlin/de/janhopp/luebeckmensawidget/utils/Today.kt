package de.janhopp.luebeckmensawidget.utils

import de.janhopp.luebeckmensawidget.api.model.MensaDay
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

val currentTime: LocalDateTime
    get() = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())

val LocalDateTime.mensaDay: LocalDate
    get() = when (date.dayOfWeek) {
        FRIDAY -> if (isAfterMensaHours) date + 3.days else date
        SATURDAY -> date + 2.days
        SUNDAY -> date + 1.days
        else -> if (isAfterMensaHours) date + 1.days else date
    }

fun DayOfWeek.format(): String = getDisplayName(TextStyle.FULL, Locale.getDefault())

fun String.toDateString() = split("-").let { (_, month, day) -> "$day.$month." }

fun MensaDay.toDisplayString() = "${localDate.dayOfWeek.format()}, ${date.toDateString()}"

val LocalDateTime.mensaApiFormat: String
    get() = "%04d-%02d-%02d".format(mensaDay.year, mensaDay.monthNumber, mensaDay.dayOfMonth)

private val LocalDateTime.isAfterMensaHours
    get() = hour >= 15

private val Int.days: DatePeriod
    get() = DatePeriod(days = this)
