package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.Meal
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.api.model.filterByDiet
import de.janhopp.luebeckmensawidget.api.model.filterDeals
import de.janhopp.luebeckmensawidget.api.model.formatPrice
import de.janhopp.luebeckmensawidget.api.model.getFor
import de.janhopp.luebeckmensawidget.theme.glanceString
import de.janhopp.luebeckmensawidget.theme.toGlance
import de.janhopp.luebeckmensawidget.ui.activity.MensaDayActivity
import de.janhopp.luebeckmensawidget.ui.components.StyledText
import de.janhopp.luebeckmensawidget.ui.utils.glanceString
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig

@Composable
fun MensaDayView(day: MensaDay, widgetConfig: MensaWidgetConfig) {
    val (showDate, useEmoji, priceGroup, filterDeals, _, locations, allergens, dietFilter) = widgetConfig
    val allergenCodes = allergens.map { it.code }

    Column(
        modifier = GlanceModifier.fillMaxSize()
            .clickable(actionStartActivity<MensaDayActivity>())
    ) {
        if (showDate)
            DateTopBar(day)

        if (day.meals.isEmpty())
            ErrorView(
                imageRes = R.drawable.no_food,
                errorMessage = glanceString(R.string.error_empty_menu),
            )
        else
            LazyColumn(
                modifier = GlanceModifier.fillMaxSize(),
            ) {
                val meals = day.meals.filterDeals(isEnabled = filterDeals).filterByDiet(dietFilter)
                val mealsByLocation = meals.groupBy { it.location }
                items(items = mealsByLocation.keys.toList()) { location ->
                    Column {
                        StyledText(
                            modifier = GlanceModifier
                                .fillMaxWidth()
                                .clickable(onClick = actionStartActivity<MensaDayActivity>())
                                .padding(horizontal = 12.dp, vertical = 2.dp)
                                .padding(top = 8.dp),
                            style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
                                .toGlance().copy(fontWeight = FontWeight.Bold),
                            text = location.name,
                        )
                        Column(modifier = GlanceModifier.padding(horizontal = 4.dp)) {
                            mealsByLocation[location]?.forEach { meal ->
                                MealView(meal, useEmoji, priceGroup, allergenCodes)
                            }
                        }
                    }
                }
            }
    }
}

@Composable
fun MealView(
    meal: Meal,
    useEmoji: Boolean,
    priceGroup: PriceGroup,
    allergenCodes: List<String>,
) {
    Column(
        modifier = GlanceModifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable(actionStartActivity<MensaDayActivity>())
    ) {
        StyledText(
            style = LocalTextStyle.current.toGlance().copy(fontWeight = FontWeight.Bold),
            text = if (useEmoji) meal.widgetName else meal.name,
        )

        val warnAllergens =
            meal.allergens.filter { it.code in allergenCodes }.mapNotNull { it.glanceString() }
                .joinToString()
        if (warnAllergens.isNotEmpty()) StyledText(text = "⚠️ $warnAllergens")

        val price = meal.price.getFor(priceGroup).formatPrice()
        if (price != null) StyledText(text = price)
    }
}
