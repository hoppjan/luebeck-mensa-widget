package de.janhopp.luebeckmensawidget.api.model

enum class DietFilter(val key: String) {
    None("none"),
    Vegetarian("vegetarian"),
    Vegan("vegan");

    companion object {
        fun fromKey(key: String): DietFilter = entries.find { it.key == key } ?: None
    }
}