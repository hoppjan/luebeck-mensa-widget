package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.filterDeals
import de.janhopp.luebeckmensawidget.theme.glanceString
import de.janhopp.luebeckmensawidget.theme.toGlance
import de.janhopp.luebeckmensawidget.ui.activity.MensaDayActivity
import de.janhopp.luebeckmensawidget.ui.components.StyledText
import de.janhopp.luebeckmensawidget.ui.utils.formatMealInfo
import de.janhopp.luebeckmensawidget.ui.utils.glanceString
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.api.model.filterByDiet

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
                items(
                    items = day.meals
                        .filterDeals(isEnabled = filterDeals)
                        .filterByDiet(dietFilter)
                ) { meal ->
                    Column(
                        modifier = GlanceModifier.padding(8.dp)
                            .clickable(actionStartActivity<MensaDayActivity>())
                    ) {
                        StyledText(
                            style = LocalTextStyle.current.toGlance()
                                .copy(fontWeight = FontWeight.Bold),
                            text = if (useEmoji) meal.widgetName else meal.name
                        )

                        val warnAllergens = meal.allergens.filter { it.code in allergenCodes }
                            .mapNotNull { it.glanceString() }
                            .joinToString()
                        if (warnAllergens.isNotEmpty())
                            StyledText(text = "⚠️ $warnAllergens")

                        val mealInfo = meal.formatMealInfo(priceGroup, locations)
                        if (mealInfo != null) StyledText(text = mealInfo)
                    }
                }
            }
    }
}
