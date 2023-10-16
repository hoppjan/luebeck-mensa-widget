package de.janhopp.luebeckmensawidget.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val currentTime: LocalDateTime
    get() = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())

val currentDay: LocalDate
    get() = currentTime.date
