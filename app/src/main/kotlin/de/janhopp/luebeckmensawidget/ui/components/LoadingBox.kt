package de.janhopp.luebeckmensawidget.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp

// credit: https://touchlab.co/loading-shimmer-in-compose/ Julia Jakubcova
fun Modifier.shimmerBackground(shape: Shape = RoundedCornerShape(4.dp)): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val color = MaterialTheme.colorScheme.primaryContainer
    val shimmerColors = listOf(
        color.copy(alpha = 0.2f),
        color.copy(alpha = 0.1f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation, y = translateAnimation),
        end = Offset(x = translateAnimation + 100f, y = translateAnimation + 100f),
        tileMode = TileMode.Mirror,
    )
    return@composed this.then(background(brush, shape))
}
