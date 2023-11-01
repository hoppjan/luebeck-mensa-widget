package de.janhopp.luebeckmensawidget.api.model

class Price(
    val euros: Short,
    val cents: Short,
) {
    fun format(
        centSeparator: Char = ',',
        addSpaceAfterPrice: Boolean = true,
        addEuroSign: Boolean = true,
    ) = buildString {
        append("$euros$centSeparator$cents")
        if (addSpaceAfterPrice) append(" ")
        if (addEuroSign) append("€")
    }
}
