package de.janhopp.luebeckmensawidget.api.model

enum class DietFilter {
    None,
    Vegetarian,
    Vegan,
    ;

    companion object {
        fun fromName(name: String): DietFilter = entries.find { it.name == name } ?: None
    }
}
