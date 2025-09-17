package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import de.janhopp.luebeckmensawidget.api.model.PriceGroup.*
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class GroupedPrices(
    val students: Float,
    val employees: Float,
    val guests: Float,
)

fun GroupedPrices.getFor(group: PriceGroup): Float = when (group) {
    Students -> students
    Employees -> employees
    Guests -> guests
}

fun Float.formatPrice() = "%.2f €".format(this)

fun Float.formatPriceOrRequest(): String = 
    if (this == 0.0f) "" else formatPrice()
