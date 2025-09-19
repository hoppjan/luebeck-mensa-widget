package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import kotlinx.serialization.Serializable

sealed interface Navigation {
    @Serializable
    data class Menu(val dayDate: String? = null)
    @Serializable
    data object Settings
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val applicationContext = LocalContext.current.applicationContext

    NavHost(
        navController = navController,
        startDestination = Navigation.Menu(),
        enterTransition = {
            scaleIn(animationSpec = tween(300), initialScale = 0.98f) +
                    fadeIn(tween(300))
        },
        exitTransition = {
            scaleOut(animationSpec = tween(300), targetScale = 0.98f) +
                    fadeOut(tween(300))
        }
    ) {
        composable<Navigation.Menu> {
            MensaDayScreen(navController)
        }
        composable<Navigation.Settings> {
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
