package de.janhopp.luebeckmensawidget.api.model

import de.janhopp.luebeckmensawidget.R

@Suppress("UNUSED")
sealed class Allergens(
    open val code: String,
    open val resId: Int,
) {
    data object Eggs : Allergens(code = "Ei", resId = R.string.allergen_eggs)
    data object Peanuts : Allergens(code = "En", resId = R.string.allergen_peanuts)
    data object Fish : Allergens(code = "Fi", resId = R.string.allergen_fish)
    data object Spelt : Allergens(code = "GlD", resId = R.string.allergen_spelt)
    data object Barley : Allergens(code = "GlG", resId = R.string.allergen_barley)
    data object Oats : Allergens(code = "GlH", resId = R.string.allergen_oats)
    data object Kamut : Allergens(code = "GlK", resId = R.string.allergen_kamut)
    data object Rye : Allergens(code = "GlR", resId = R.string.allergen_rye)
    data object Wheat : Allergens(code = "GlW", resId = R.string.allergen_wheat)
    data object Crustaceans : Allergens(code = "Kr", resId = R.string.allergen_crustaceans)
    data object Lupine : Allergens(code = "Lu", resId = R.string.allergen_lupine)
    data object MilkLactose : Allergens(code = "Mi", resId = R.string.allergen_milk_lactose)
    data object CashewNuts : Allergens(code = "NC", resId = R.string.allergen_cashew_nuts)
    data object Hazelnuts : Allergens(code = "NH", resId = R.string.allergen_hazelnuts)
    data object MacadamiaNuts : Allergens(code = "NMc", resId = R.string.allergen_macadamia_nuts)
    data object Almonds : Allergens(code = "NMn", resId = R.string.allergen_almonds)
    data object BrazilNuts : Allergens(code = "NPa", resId = R.string.allergen_brazil_nuts)
    data object Pecans : Allergens(code = "NPe", resId = R.string.allergen_pecans)
    data object Pistachios : Allergens(code = "NPi", resId = R.string.allergen_pistachios)
    data object Walnuts : Allergens(code = "NW", resId = R.string.allergen_walnuts)
    data object Sesame : Allergens(code = "Se", resId = R.string.allergen_sesame)
    data object Mustard : Allergens(code = "Sf", resId = R.string.allergen_mustard)
    data object Celery : Allergens(code = "Sl", resId = R.string.allergen_celery)
    data object Soy : Allergens(code = "So", resId = R.string.allergen_soy)
    data object SulfurDioxide : Allergens(code = "Sw", resId = R.string.allergen_sulfur_dioxide)
    data object Molluscs : Allergens(code = "Wt", resId = R.string.allergen_molluscs)

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
