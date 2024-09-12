package de.janhopp.luebeckmensawidget.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.storage.Option
import de.janhopp.luebeckmensawidget.storage.OptionsStorage

@Composable
fun MensaDayView(day: MensaDay) {
    val options = OptionsStorage(LocalContext.current)
    var showDate by remember { mutableStateOf(Option.ShowDate.defaultValue) }
    var useEmoji by remember { mutableStateOf(Option.UseEmoji.defaultValue) }

    LaunchedEffect(Unit) {
        showDate = options.get(Option.ShowDate)
        useEmoji = options.get(Option.UseEmoji)
    }
    LazyColumn(
        modifier = GlanceModifier.fillMaxSize(),
    ) {
        if (showDate)
            item {
                Text(
                    text = day.date,
                    modifier = GlanceModifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 10.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                )
            }

        items(
            items = day.meals
        ) { meal ->
            MealView(meal, useEmoji)
        }
    }
}
