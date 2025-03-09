package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.glance.appwidget.updateAll
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.janhopp.luebeckmensawidget.ui.config.WidgetConfigurationScreen
import de.janhopp.luebeckmensawidget.widget.MensaWidget
import kotlinx.coroutines.launch

object Navigation {
    const val MENU = "MENU"
    const val SETTINGS = "SETTINGS"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val applicationContext = LocalContext.current.applicationContext

    NavHost(
        navController = navController,
        startDestination = Navigation.MENU,
    ) {
        composable(Navigation.MENU) {
            MensaDayScreen(navController)
        }
        composable(Navigation.SETTINGS) {
            WidgetConfigurationScreen(
                navController = navController,
                onConfigurationFinished = {
                    coroutineScope.launch {
                        MensaWidget().updateAll(applicationContext)
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}
