package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.janhopp.luebeckmensawidget.R
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.utils.Icons
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaApiFormat
import de.janhopp.luebeckmensawidget.utils.mensaDay
import de.janhopp.luebeckmensawidget.utils.toDisplayString
import de.janhopp.luebeckmensawidget.widget.MensaWidgetConfig
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.plus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MensaMenuScreen(
    navController: NavHostController,
) {
    val appContext = LocalContext.current.applicationContext
    val storage = MenuStorage(appContext)
    val options = OptionsStorage(appContext)
    var widgetConfig by remember { mutableStateOf(MensaWidgetConfig()) }
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isRefreshing = true
        widgetConfig = options.getWidgetConfig()
        isRefreshing = false
    }

    val chosenIndex = remember { mutableIntStateOf(0) }
    val mensaDays = storage.getMensaDaysFrom(currentTime.mensaDay)
        .map { it.takeFiveOrUntilWeekend() }
        .collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.title_menu)) },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Navigation.Settings) },
                    ) {
                        Icon(Icons.paintSettings(), contentDescription = null)
                    }
                    IconButton(
                        onClick = { navController.navigate(Navigation.About) },
                    ) {
                        Icon(Icons.paintInfo(), contentDescription = null)
                    }
                }
            )
        },
    ) {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    storage.setMensaDays(MensaApi().getAllDaysMeals(widgetConfig.locations))
                    isRefreshing = false
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(paddingValues = it),
            ) {
                PrimaryTabRow(
                    selectedTabIndex = chosenIndex.intValue,
                ) {
                    mensaDays.value.forEachIndexed { index, day ->
                        Tab(
                            selected = chosenIndex.intValue == index,
                            onClick = {
                                chosenIndex.intValue = index
                            },
                            text = {
                                Text(
                                    text = day.toDisplayString(),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        )
                    }
                }

                if (isRefreshing)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                else if (mensaDays.value.getOrNull(index = chosenIndex.intValue) == null)
                    MensaErrorView(
                        imageRes = R.drawable.error,
                        errorMessage = stringResource(R.string.error_could_not_load_menu),
                    )
                else
                    MensaDayView(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        day = mensaDays.value[chosenIndex.intValue],
                        widgetConfig = widgetConfig,
                    )
            }
        }
    }
}

private fun List<MensaDay>.takeFiveOrUntilWeekend() = take(5).let { days ->
    val today = currentTime.mensaDay
    buildList {
        addAll(days)
        while (this@buildList.size < 5) {
            val newDate = (today + DatePeriod(days = this@buildList.size))
            if (newDate.dayOfWeek in listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)) break
            add(MensaDay(newDate.mensaApiFormat, emptyList()))
        }
    }
}
