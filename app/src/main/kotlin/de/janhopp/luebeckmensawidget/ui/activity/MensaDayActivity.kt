package de.janhopp.luebeckmensawidget.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme

class MensaDayActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MensaTheme {
                Navigation()
            }
        }
    }
}
