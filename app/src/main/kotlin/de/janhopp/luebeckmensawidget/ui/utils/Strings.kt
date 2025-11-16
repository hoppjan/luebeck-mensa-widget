package de.janhopp.luebeckmensawidget.ui.utils

import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.Allergen
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.Allergens
import de.janhopp.luebeckmensawidget.api.model.City
import de.janhopp.luebeckmensawidget.api.model.Meal
import de.janhopp.luebeckmensawidget.api.model.MensaLocation
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.api.model.formatPrice
import de.janhopp.luebeckmensawidget.api.model.getFor
import de.janhopp.luebeckmensawidget.theme.glanceString

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
fun City.stringRes(): String =
    stringResource(
        when (this) {
            City.Kiel -> R.string.city_kiel
            City.Luebeck -> R.string.city_luebeck
            City.Flensburg -> R.string.city_flensburg
            City.Heide -> R.string.city_heide
            City.Wedel -> R.string.city_wedel
            City.Osterroenfeld -> R.string.city_osterroenfeld
        }
    )

@Composable
fun Location.stringRes(): String =
    stringResource(
        when (this) {
            Location.MensaLuebeck -> R.string.location_mensa_luebeck
            Location.CafeteriaLuebeck -> R.string.location_cafeteria_luebeck
            Location.MusikhochschuleLuebeck -> R.string.location_musikhochschule_luebeck
            Location.BitsAndBytes -> R.string.location_bits_and_bytes
            Location.Mensa1Kiel -> R.string.location_mensa1_kiel
            Location.Cafeteria1Kiel -> R.string.location_cafeteria1_kiel
            Location.Mensa2Kiel -> R.string.location_mensa2_kiel
            Location.Cafeteria2Kiel -> R.string.location_cafeteria2_kiel
            Location.KesselhausKiel -> R.string.location_kesselhaus_kiel
            Location.SchwentineMensaKiel -> R.string.location_schwentine_kiel
            Location.AmericanDinerKiel -> R.string.location_american_diner_kiel
            Location.MensaDocksideKiel -> R.string.location_dockside_kiel
            Location.MensaHeide -> R.string.location_mensa_heide
            Location.MensaFlensburg -> R.string.location_mensa_flensburg
            Location.CafeteriaAFlensburg -> R.string.location_cafeteria1_flensburg
            Location.CafeteriaBFlensburg -> R.string.location_cafeteria2_flensburg
            Location.MensaOsterroenfeld -> R.string.location_mensa_osterroenfeld
            Location.CafeteriaWedel -> R.string.location_cafeteria_wedel
        }
    )

@Composable
fun MensaLocation.stringRes() = Location.fromCode(code)?.stringRes()

@Composable
fun Allergen.resId() = Allergens.allAllergens.find { it.code == code }?.resId

@Composable
fun Allergen.glanceString() = resId()?.let { glanceString(it) }
