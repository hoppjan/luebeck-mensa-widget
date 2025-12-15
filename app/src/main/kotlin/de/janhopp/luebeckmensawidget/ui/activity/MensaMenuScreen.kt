package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
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
    var mensaDays by remember { mutableStateOf(getEmptyMensaDaysFrom(currentTime.mensaDay)) }
    val pagerState = rememberPagerState(pageCount = { mensaDays.count() })
    val scope = rememberCoroutineScope()

    suspend fun refreshMensaDays() {
        storage.setMensaDays(MensaApi().getAllDaysMeals(widgetConfig.locations))
        mensaDays = storage.getMensaDaysFrom(date = currentTime.mensaDay).first()
    }
    LaunchedEffect(Unit) {
        isRefreshing = true
        widgetConfig = options.getWidgetConfig()
        refreshMensaDays()
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
        Column(
            modifier = Modifier.padding(paddingValues = it),
        ) {
            PrimaryTabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                mensaDays.forEachIndexed { index, day ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            pagerState.requestScrollToPage(index)
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

            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = {
                    scope.launch {
                        isRefreshing = true
                        refreshMensaDays()
                        isRefreshing = false
                    }
                }
            ) {
                HorizontalPager(state = pagerState) { selectedIndex ->
                    if (isRefreshing)
                        ProgressIndicatorView()
                    else if (mensaDays.getOrNull(index = selectedIndex) == null)
                        MensaErrorView(
                            imageRes = R.drawable.error,
                            errorMessage = stringResource(R.string.error_could_not_load_menu),
                        )
                    else
                        MensaDayView(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            day = mensaDays[selectedIndex],
                            widgetConfig = widgetConfig,
                        )
                }
            }
        }
    }
}

private fun getEmptyMensaDaysFrom(date: LocalDate): List<MensaDay> = (0..<5).map {
    MensaDay((date + DatePeriod(days = it)).mensaApiFormat, emptyList())
}
