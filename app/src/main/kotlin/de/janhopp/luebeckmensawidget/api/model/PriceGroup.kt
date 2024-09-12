package de.janhopp.luebeckmensawidget.api.model

enum class PriceGroup {
    Students, Employees, Guests;

    companion object {
        val names: List<String>
            get() = entries.map { it.name }
    }
}

