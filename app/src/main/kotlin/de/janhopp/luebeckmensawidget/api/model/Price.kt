package de.janhopp.luebeckmensawidget.api.model

import kotlinx.serialization.Serializable

@Serializable
class Price(
    val students: Float,
    val employees: Float,
    val guests: Float
)