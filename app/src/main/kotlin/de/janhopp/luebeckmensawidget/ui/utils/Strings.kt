package de.janhopp.luebeckmensawidget.ui.utils

import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import de.janhopp.luebeckmensawidget.R
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
