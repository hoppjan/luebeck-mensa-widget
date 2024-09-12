package de.janhopp.luebeckmensawidget.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.fillMaxSize
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage

@Composable
fun MensaDayView(day: MensaDay) {
    val options = OptionsStorage(LocalContext.current)
    var showDate by remember { mutableStateOf(Option.ShowDate.defaultValue) }
    var useEmoji by remember { mutableStateOf(Option.UseEmoji.defaultValue) }
    var priceGroup by remember { mutableStateOf(Option.PriceGroup.defaultValue) }

    LaunchedEffect(Unit) {
        showDate = options.get(Option.ShowDate)
        useEmoji = options.get(Option.UseEmoji)
        priceGroup = options.getString(Option.PriceGroup)
    }
    LazyColumn(
        modifier = GlanceModifier.fillMaxSize(),
    ) {
        if (showDate)
            item { DateTopBar(day) }

        items(
            items = day.meals
        ) { meal ->
            MealView(meal, useEmoji, PriceGroup.valueOf(priceGroup))
        }
    }
}
