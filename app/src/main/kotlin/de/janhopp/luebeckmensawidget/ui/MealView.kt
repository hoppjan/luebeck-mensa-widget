package de.janhopp.luebeckmensawidget.ui

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import de.janhopp.luebeckmensawidget.api.model.Meal
import de.janhopp.luebeckmensawidget.theme.toGlance
import de.janhopp.luebeckmensawidget.ui.components.StyledText

@Composable
fun MealView(meal: Meal) {
    Column(
        modifier = GlanceModifier.padding(8.dp)
    ) {
        StyledText(
            style = LocalTextStyle.current.toGlance()
                .copy(fontWeight = FontWeight.Bold),
            text = meal.widgetName
        )
        StyledText(
            text = meal.studentPrice
        )
    }
}
