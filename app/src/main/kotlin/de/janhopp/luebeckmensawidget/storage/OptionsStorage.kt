package de.janhopp.luebeckmensawidget.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import de.janhopp.luebeckmensawidget.api.model.PriceGroup.Students
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

    suspend fun getString(option: Option<String>): String {
        return dataStore.data.map { preferences -> preferences[option.toStringKey()] }.firstOrNull()
            ?: option.defaultValue
    }

    suspend fun setString(option: Option<String>, value: String) {
        dataStore.edit { preferences -> preferences[option.toStringKey()] = value }
    }

    private companion object {
        fun Option<Boolean>.toKey() = booleanPreferencesKey(key)
        fun Option<String>.toStringKey() = stringPreferencesKey(key)
    }
}

sealed class Option<T>(
    val key: String,
    val defaultValue: T,
) {
    data object ShowDate : Option<Boolean>(key = "show_date", defaultValue = false)
    data object UseEmoji : Option<Boolean>(key = "use_emoji", defaultValue = true)
    data object PriceGroup : Option<String>(key = "price_group", defaultValue = Students.name)
}
