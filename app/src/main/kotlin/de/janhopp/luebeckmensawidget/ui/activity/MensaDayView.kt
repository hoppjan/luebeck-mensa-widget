package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.filterDeals
import de.janhopp.luebeckmensawidget.ui.utils.formatMealInfo
import de.janhopp.luebeckmensawidget.ui.utils.resId
import de.janhopp.luebeckmensawidget.utils.toDisplayString
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig

@Composable
fun MensaDayView(
    modifier: Modifier = Modifier,
    day: MensaDay,
    widgetConfig: MensaWidgetConfig,
) {
    val (showDate, useEmoji, priceGroup, filterDeals, _, locations, allergens) = widgetConfig
    val allergenCodes = allergens.map { it.code }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (showDate)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
                    .padding(start = 2.dp),
            ) {
                Text(text = day.toDisplayString())
            }

        if (day.meals.isEmpty())
            MensaErrorView(
                imageRes = R.drawable.no_food,
                errorMessage = stringResource(R.string.error_empty_menu),
            )
        else
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(day.meals.filterDeals(isEnabled = filterDeals)) { meal ->
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = if (useEmoji) meal.widgetName else meal.name,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        )

                        val warnAllergens = meal.allergens.filter { it.code in allergenCodes }
                            .mapNotNull { it.resId()?.let { id -> stringResource(id) } }
                            .joinToString()
                        if (warnAllergens.isNotEmpty())
                            Text(text = "⚠️ $warnAllergens")

                        val mealInfo = meal.formatMealInfo(priceGroup, locations)
                        if (mealInfo != null) Text(text = mealInfo)
                    }
                }
            }
    }
}
