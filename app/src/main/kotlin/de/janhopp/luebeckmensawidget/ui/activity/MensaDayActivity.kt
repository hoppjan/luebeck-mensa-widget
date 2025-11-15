package de.janhopp.luebeckmensawidget.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import de.janhopp.luebeckmensawidget.SyncWorker.Companion.enqueueSyncWork
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme

class MensaDayActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.enqueueSyncWork()
        WindowCompat.enableEdgeToEdge(window)
        setContent {
            MensaTheme {
                Navigation()
            }
        }
    }
}
