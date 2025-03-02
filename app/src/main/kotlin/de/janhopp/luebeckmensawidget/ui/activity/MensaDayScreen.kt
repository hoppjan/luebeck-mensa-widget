package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaDay
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MensaDayScreen() {
    val appContext = LocalContext.current.applicationContext
    val storage = MenuStorage(appContext)
    val options = OptionsStorage(appContext)
    var widgetConfig by remember { mutableStateOf(MensaWidgetConfig()) }
    var isUpdating by remember { mutableStateOf(false) }
    val todayFromStorage = storage.getMensaDay(currentTime.mensaDay).collectAsState(null)

    LaunchedEffect(Unit) {
        isUpdating = true
        widgetConfig = options.getWidgetConfig()
        isUpdating = false
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }
            )
        },
    ) {
        if (todayFromStorage.value != null)
            MensaDayView(
                Modifier.padding(it),
                todayFromStorage.value!!,
                widgetConfig
            )
        else
            Text("...")
    }
}

@Composable
@Preview
fun Preview_MensaDayScreen() = MensaTheme {
    MensaDayScreen()
}
