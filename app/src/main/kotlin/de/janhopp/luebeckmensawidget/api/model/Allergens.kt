package de.janhopp.luebeckmensawidget.api.model

import androidx.annotation.Keep
import de.janhopp.luebeckmensawidget.R

@Suppress("UNUSED")
@Keep
sealed class Allergens(
    open val code: String,
    open val resId: Int,
) {
    @Keep data object Eggs : Allergens(code = "Ei", resId = R.string.allergen_eggs)
    @Keep data object Peanuts : Allergens(code = "En", resId = R.string.allergen_peanuts)
    @Keep data object Fish : Allergens(code = "Fi", resId = R.string.allergen_fish)
    @Keep data object Spelt : Allergens(code = "GlD", resId = R.string.allergen_spelt)
    @Keep data object Barley : Allergens(code = "GlG", resId = R.string.allergen_barley)
    @Keep data object Oats : Allergens(code = "GlH", resId = R.string.allergen_oats)
    @Keep data object Kamut : Allergens(code = "GlK", resId = R.string.allergen_kamut)
    @Keep data object Rye : Allergens(code = "GlR", resId = R.string.allergen_rye)
    @Keep data object Wheat : Allergens(code = "GlW", resId = R.string.allergen_wheat)
    @Keep data object Crustaceans : Allergens(code = "Kr", resId = R.string.allergen_crustaceans)
    @Keep data object Lupine : Allergens(code = "Lu", resId = R.string.allergen_lupine)
    @Keep data object MilkLactose : Allergens(code = "Mi", resId = R.string.allergen_milk_lactose)
    @Keep data object CashewNuts : Allergens(code = "NC", resId = R.string.allergen_cashew_nuts)
    @Keep data object Hazelnuts : Allergens(code = "NH", resId = R.string.allergen_hazelnuts)
    @Keep data object MacadamiaNuts : Allergens(code = "NMc", resId = R.string.allergen_macadamia_nuts)
    @Keep data object Almonds : Allergens(code = "NMn", resId = R.string.allergen_almonds)
    @Keep data object BrazilNuts : Allergens(code = "NPa", resId = R.string.allergen_brazil_nuts)
    @Keep data object Pecans : Allergens(code = "NPe", resId = R.string.allergen_pecans)
    @Keep data object Pistachios : Allergens(code = "NPi", resId = R.string.allergen_pistachios)
    @Keep data object Walnuts : Allergens(code = "NW", resId = R.string.allergen_walnuts)
    @Keep data object Sesame : Allergens(code = "Se", resId = R.string.allergen_sesame)
    @Keep data object Mustard : Allergens(code = "Sf", resId = R.string.allergen_mustard)
    @Keep data object Celery : Allergens(code = "Sl", resId = R.string.allergen_celery)
    @Keep data object Soy : Allergens(code = "So", resId = R.string.allergen_soy)
    @Keep data object SulfurDioxide : Allergens(code = "Sw", resId = R.string.allergen_sulfur_dioxide)
    @Keep data object Molluscs : Allergens(code = "Wt", resId = R.string.allergen_molluscs)

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
