package de.janhopp.luebeckmensawidget.ui

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.fillMaxSize
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.MensaLocation

@Composable
fun MensaDayView(day: MensaDay) {
    LazyColumn(
        modifier = GlanceModifier.fillMaxSize(),
    ) {
        items(
            items = day.meals
                .filter { it.location == MensaLocation.Mensa },
        ) { meal ->
            MealView(meal)
        }
    }
}
