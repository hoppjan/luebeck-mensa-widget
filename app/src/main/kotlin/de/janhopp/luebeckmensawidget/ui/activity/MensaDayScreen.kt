package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaDay
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MensaDayScreen(
    navController: NavHostController = rememberNavController(),
) {
    val appContext = LocalContext.current.applicationContext
    val storage = MenuStorage(appContext)
    val options = OptionsStorage(appContext)
    var widgetConfig by remember { mutableStateOf(MensaWidgetConfig()) }
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val todayFromStorage = storage.getMensaDay(currentTime.mensaDay).collectAsState(null)

    LaunchedEffect(Unit) {
        isRefreshing = true
        widgetConfig = options.getWidgetConfig()
        isRefreshing = false
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.title_menu)) },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Navigation.SETTINGS) },
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }
            )
        },
    ) {
        PullToRefreshBox(
            modifier = Modifier.padding(it),
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    MensaApi().getMealsToday(widgetConfig.locations)
                        ?.let { mealsToday -> storage.setMensaDays(listOf(mealsToday)) }
                    isRefreshing = false
                }
            }
        ) {
            if (isRefreshing)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            else if (todayFromStorage.value == null)
                MensaErrorView(
                    imageRes = R.drawable.error,
                    errorMessage = stringResource(R.string.error_could_not_load_menu),
                )
            else
                MensaDayView(
                    Modifier.padding(horizontal = 8.dp),
                    todayFromStorage.value!!,
                    widgetConfig
                )
        }
    }
}

@Composable
@Preview
fun Preview_MensaDayScreen() = MensaTheme {
    MensaDayScreen()
}
