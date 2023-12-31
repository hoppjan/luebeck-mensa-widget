package de.janhopp.luebeckmensawidget.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.LocalContext
import androidx.glance.color.isNightMode

@Composable
fun AppTheme(
    useDarkTheme: Boolean = LocalContext.current.isNightMode,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LocalContext.current.colorScheme(useDarkTheme),
        typography = Typography(
            color = if (useDarkTheme) Color.White else Color.Black // fixes dark mode font color
        ),
        content = content
    )
}
