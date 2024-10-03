package de.janhopp.luebeckmensawidget.ui

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.filterDeals
import de.janhopp.luebeckmensawidget.theme.glanceString
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig

@Composable
fun MensaDayView(day: MensaDay, widgetConfig: MensaWidgetConfig) {
    val (showDate, useEmoji, priceGroup, filterDeals) = widgetConfig

    Column {
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
                ) { meal ->
                    MealView(meal, useEmoji, priceGroup)
                }
            }
    }
}
