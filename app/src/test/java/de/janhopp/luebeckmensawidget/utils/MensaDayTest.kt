package de.janhopp.luebeckmensawidget.utils

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class MensaDayTest {
    private fun getDate(weekDay: DayOfWeek, isAfterHours: Boolean) = LocalDateTime(
        year = 2024,
        monthNumber = 9,
        dayOfMonth = 23 + weekDay.ordinal,
        hour = if (isAfterHours) 16 else 12,
        minute = 34,
        second = 56,
    )

    private val LocalDateTime.isAfterMensaHours
        get() = hour >= 15

    private val Int.days: DatePeriod
        get() = DatePeriod(days = this)

    private val LocalDateTime.oldMensaDay: LocalDate
        get() = when {
            date.dayOfWeek == FRIDAY && isAfterMensaHours -> date + 3.days
            date.dayOfWeek == SATURDAY -> date + 2.days
            date.dayOfWeek == SUNDAY || isAfterMensaHours -> date + 1.days
            else -> date
        }

    @Test
    fun `mensaDay should return same week day as old implementation`() {
        for (weekDay in DayOfWeek.entries) {
            for (isAfterHours in listOf(false, true)) {
                val date = getDate(weekDay, isAfterHours)
                assertEquals(date.oldMensaDay.dayOfWeek, date.mensaDay.dayOfWeek)
            }
        }
    }

    @Test
    fun `mensaDay should return different week day given isAfterHours true`() {
        for (weekDay in DayOfWeek.entries) {
            val date = getDate(weekDay, isAfterHours = true)
            assertNotEquals(date.dayOfWeek, date.mensaDay.dayOfWeek)
        }
    }

    @Test
    fun `getDate should return same week day given on working days & isAfterHours false`() {
        val workingDays = listOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
        for (weekDay in workingDays) {
            val date = getDate(weekDay, isAfterHours = false)
            assertEquals(date.dayOfWeek, date.mensaDay.dayOfWeek)
        }
    }

    @Test
    fun `getDate should return monday given friday & isAfterHours true`() {
        assertEquals(MONDAY, getDate(FRIDAY, isAfterHours = true).mensaDay.dayOfWeek)
    }

    @Test
    fun `getDate should return monday given saturday & isAfterHours false`() {
        assertEquals(MONDAY, getDate(SATURDAY, isAfterHours = false).mensaDay.dayOfWeek)
    }

    @Test
    fun `getDate should return monday given saturday & isAfterHours true`() {
        assertEquals(MONDAY, getDate(SATURDAY, isAfterHours = true).mensaDay.dayOfWeek)
    }

    @Test
    fun `getDate should return monday given sunday & isAfterHours false`() {
        assertEquals(MONDAY, getDate(SUNDAY, isAfterHours = false).mensaDay.dayOfWeek)
    }

    @Test
    fun `getDate should return monday given sunday & isAfterHours true`() {
        assertEquals(MONDAY, getDate(SUNDAY, isAfterHours = true).mensaDay.dayOfWeek)
    }
}
