package de.janhopp.luebeckmensawidget.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "options_storage")

class OptionsStorage(
    context: Context,
) {
    private val dataStore = context.dataStore

    suspend fun get(option: Option<Boolean>): Boolean {
        return dataStore.data.map { preferences -> preferences[option.toKey()] }.firstOrNull()
            ?: option.defaultValue
    }

    suspend fun set(option: Option<Boolean>, value: Boolean) {
        dataStore.edit { preferences -> preferences[option.toKey()] = value }
    }

    private companion object {
        fun Option<Boolean>.toKey() = booleanPreferencesKey(key)
    }
}

sealed class Option<T>(
    val key: String,
    val defaultValue: T,
) {
    data object ShowDate : Option<Boolean>(key = "show_date", defaultValue = false)
}
