package de.janhopp.luebeckmensawidget.ui.utils

import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.Allergen
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.Allergens
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
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
fun Location.stringRes(): String =
    stringResource(
        when (this) {
            Location.MensaLuebeck -> R.string.location_mensa_luebeck
            Location.CafeteriaLuebeck -> R.string.location_cafeteria_luebeck
            Location.MusikhochschuleLuebeck -> R.string.location_musikhochschule_luebeck
            Location.BitsAndBytes -> R.string.location_bits_and_bytes
        }
    )

@Composable
fun Allergen.resId() = Allergens.allAllergens.find { it.code == code }?.resId

@Composable
fun Allergen.glanceString() = resId()?.let { glanceString(it) }
