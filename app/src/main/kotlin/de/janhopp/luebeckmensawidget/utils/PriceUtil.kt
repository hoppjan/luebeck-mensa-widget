package de.janhopp.luebeckmensawidget.utils

import de.janhopp.luebeckmensawidget.api.model.Meal
import de.janhopp.luebeckmensawidget.api.model.Price

private const val PRICE_FORMAT = "[0-9]{1,2},[0-9]{1,2} €"

fun String.isPriceFormat() =
    matches(Regex(PRICE_FORMAT))

fun String.toPrice(): Price? =
    if (isPriceFormat())
        Price(
            euros = substringBefore(",").toShort(),
            cents = substringAfter(",")
                .substringBefore(" €").toShort(),
        )
    else null

fun String.isThreePriceFormat() =
    matches(Regex("$PRICE_FORMAT / $PRICE_FORMAT / $PRICE_FORMAT"))

fun String.toPrices() =
    if (isThreePriceFormat())
        split(" / ")
            .let { (student, employee, guest) ->
                Triple(student.toPrice(), employee.toPrice(), guest.toPrice())
            }
    else null

val Meal.studentPrice: Price?
    get() = price.toPrices()?.first

val Meal.employeePrice: Price?
    get() = price.toPrices()?.second

val Meal.guestPrice: Price?
    get() = price.toPrices()?.third
