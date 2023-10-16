package de.janhopp.luebeckmensawidget.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme

fun Context.colorScheme(useDarkTheme: Boolean) = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        if (useDarkTheme)
            dynamicDarkColorScheme(this)
        else
            dynamicLightColorScheme(this)
    }
    useDarkTheme -> darkColorScheme()
    else -> lightColorScheme()
}
