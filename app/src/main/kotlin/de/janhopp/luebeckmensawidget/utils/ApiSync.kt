package de.janhopp.luebeckmensawidget.utils

import android.content.Context
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.widget.getWidgetConfig
import kotlinx.coroutines.flow.firstOrNull

suspend fun Context.syncToday(): MensaDay? = syncToday(
    api = MensaApi(),
    config = OptionsStorage(applicationContext),
    storage = MenuStorage(applicationContext),
)

suspend fun syncToday(
    api: MensaApi,
    config: OptionsStorage,
    storage: MenuStorage,
): MensaDay? {
    val todayFromStorage = storage.getMensaDay(currentTime.mensaDay).firstOrNull()
    val todayFromApi = api.getMealsToday(config.getWidgetConfig().locations)

    if (todayFromApi != null) {
        storage.setMensaDays(listOf(todayFromApi))
    }
    return when {
        todayFromApi != null -> todayFromApi
        todayFromStorage != null -> todayFromStorage
        else -> null
    }
}
