package de.janhopp.luebeckmensawidget.ui.config

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.ui.theme.MensaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetConfigurationScreen(
    navController: NavHostController = rememberNavController(),
    onConfigurationFinished: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.widget_settings_title)) },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null)
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                        }
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onConfigurationFinished,
            ) {
                Text(text = stringResource(R.string.finish_configuration))
            }
        }
    ) {
        ConfigurationList(
            modifier = Modifier.padding(it),
        )
    }
}

@Composable
@Preview
fun Preview_WidgetConfigurationScreen() = MensaTheme {
    WidgetConfigurationScreen(
        onConfigurationFinished = {},
    )
}
