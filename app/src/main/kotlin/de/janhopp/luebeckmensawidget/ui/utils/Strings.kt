package de.janhopp.luebeckmensawidget.ui.utils

import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.Allergens
import de.janhopp.luebeckmensawidget.api.model.PriceGroup

@Composable
fun PriceGroup.stringRes(): String =
    stringResource(
        when (this) {
            PriceGroup.Students -> R.string.price_group_students
            PriceGroup.Employees -> R.string.price_group_employees
            PriceGroup.Guests -> R.string.price_group_guests
        }
    )

@Composable
fun Location.stringRes(): String =
    stringResource(
        when (this) {
            Location.MensaLuebeck -> R.string.location_mensa_luebeck
            Location.CafeteriaLuebeck -> R.string.location_cafeteria_luebeck
            Location.MusikhochschuleLuebeck -> R.string.location_musikhochschule_luebeck
        }
    )

@Composable
fun Allergens.stringRes(): String =
    stringResource(
        when (this) {
            Allergens.Eggs -> R.string.allergen_eggs
            Allergens.Peanuts -> R.string.allergen_peanuts
            Allergens.Fish -> R.string.allergen_fish
            Allergens.Spelt -> R.string.allergen_spelt
            Allergens.Barley -> R.string.allergen_barley
            Allergens.Oats -> R.string.allergen_oats
            Allergens.Kamut -> R.string.allergen_kamut
            Allergens.Rye -> R.string.allergen_rye
            Allergens.Wheat -> R.string.allergen_wheat
            Allergens.Crustaceans -> R.string.allergen_crustaceans
            Allergens.Lupine -> R.string.allergen_lupine
            Allergens.MilkLactose -> R.string.allergen_milk_lactose
            Allergens.CashewNuts -> R.string.allergen_cashew_nuts
            Allergens.Hazelnuts -> R.string.allergen_hazelnuts
            Allergens.MacadamiaNuts -> R.string.allergen_macadamia_nuts
            Allergens.Almonds -> R.string.allergen_almonds
            Allergens.BrazilNuts -> R.string.allergen_brazil_nuts
            Allergens.Pecans -> R.string.allergen_pecans
            Allergens.Pistachios -> R.string.allergen_pistachios
            Allergens.Walnuts -> R.string.allergen_walnuts
            Allergens.Sesame -> R.string.allergen_sesame
            Allergens.Mustard -> R.string.allergen_mustard
            Allergens.Celery -> R.string.allergen_celery
            Allergens.Soy -> R.string.allergen_soy
            Allergens.SulfurDioxide -> R.string.allergen_sulfur_dioxide
            Allergens.Molluscs -> R.string.allergen_molluscs
        }
    )
