package de.janhopp.luebeckmensawidget.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

val currentTime: LocalDateTime
    get() = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())

val LocalDateTime.mensaDay: LocalDate
    get() = if (isAfterMensaHours) date + 1.days else date

private val LocalDateTime.isAfterMensaHours
    get() = hour >= 15

private val Int.days: DatePeriod
    get() = DatePeriod(days = this)
