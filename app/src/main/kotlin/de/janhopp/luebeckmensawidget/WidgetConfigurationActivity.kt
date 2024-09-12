package de.janhopp.luebeckmensawidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import de.janhopp.luebeckmensawidget.ui.config.WidgetConfigurationScreen
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme

class WidgetConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MensaTheme {
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
