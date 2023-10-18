package de.janhopp.luebeckmensawidget.storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

private val Context.dataStore by preferencesDataStore(name = "menu_storage")

class MenuStorage(
    context: Context,
){
    private val dataStore = context.dataStore

    fun getMensaDay(date: LocalDate): Flow<MensaDay?> {
        return dataStore.data.map { preferences ->
            MensaDay.fromJsonOrNull(preferences[date.toKey()])
        }
    }

    private suspend fun setMensaDay(menu: MensaDay) {
        dataStore.edit { preferences ->
            preferences[menu.localDate.toKey()] = menu.toJson()
        }
    }

    suspend fun setMensaDays(menus: List<MensaDay>) {
        menus.forEach { menu -> setMensaDay(menu) }
    }

    private companion object {
        fun LocalDate.toKey(): Preferences.Key<String> {
            return stringPreferencesKey(name = toKeyString())
        }

        fun LocalDate.toKeyString() = "$year-$month-$dayOfMonth"
    }
}
