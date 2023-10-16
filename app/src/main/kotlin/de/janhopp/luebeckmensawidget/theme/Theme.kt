package de.janhopp.luebeckmensawidget.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.LocalContext
import android.content.Context
import android.content.res.Configuration

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkMode(LocalContext.current),
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

fun isSystemInDarkMode(context: Context): Boolean {
    val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES
}
