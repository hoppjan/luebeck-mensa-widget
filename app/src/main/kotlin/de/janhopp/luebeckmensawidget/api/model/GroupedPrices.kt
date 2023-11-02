package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
class GroupedPrices(
    val students: Float,
    val employees: Float,
    val guests: Float,
)

fun Float.formatPrice() = "%.2f €".format(this)
