package de.janhopp.luebeckmensawidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import de.janhopp.luebeckmensawidget.ui.config.WidgetConfigurationScreen

class WidgetConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
            ) {
                WidgetConfigurationScreen(
                    onConfigurationFinished = {
                        setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }
}
