package de.janhopp.luebeckmensawidget.api.model

sealed class Allergens(
    open val code: String,
) {
    data object Eggs : Allergens(code = "Ei")
    data object Peanuts : Allergens(code = "En")
    data object Fish : Allergens(code = "Fi")
    data object Spelt : Allergens(code = "GlD")
    data object Barley : Allergens(code = "GlG")
    data object Oats : Allergens(code = "GlH")
    data object Kamut : Allergens(code = "GlK")
    data object Rye : Allergens(code = "GlR")
    data object Wheat : Allergens(code = "GlW")
    data object Crustaceans : Allergens(code = "Kr")
    data object Lupine : Allergens(code = "Lu")
    data object MilkLactose : Allergens(code = "Mi")
    data object CashewNuts : Allergens(code = "NC")
    data object Hazelnuts : Allergens(code = "NH")
    data object MacadamiaNuts : Allergens(code = "NMc")
    data object Almonds : Allergens(code = "NMn")
    data object BrazilNuts : Allergens(code = "NPa")
    data object Pecans : Allergens(code = "NPe")
    data object Pistachios : Allergens(code = "NPi")
    data object Walnuts : Allergens(code = "NW")
    data object Sesame : Allergens(code = "Se")
    data object Mustard : Allergens(code = "Sf")
    data object Celery : Allergens(code = "Sl")
    data object Soy : Allergens(code = "So")
    data object SulfurDioxide : Allergens(code = "Sw")
    data object Molluscs : Allergens(code = "Wt")

    companion object {
        val allAllergens: List<Allergens> =
            Allergens::class.sealedSubclasses.mapNotNull { it.objectInstance }

        fun fromStringSet(strings: Set<String>): Set<Allergens> = strings
            .mapNotNull { code ->
                allAllergens.find { allergen -> allergen.code == code }
            }
            .toSet()
    }
}

fun Iterable<Allergens>.toStringSet(): Set<String> = map { it.code }.toSet()
