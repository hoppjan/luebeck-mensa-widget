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

fun Float.formatPrice() = if (this != 0f) "%.2f €".format(this) else null
