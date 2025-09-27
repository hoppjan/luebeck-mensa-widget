package de.janhopp.luebeckmensawidget.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import de.janhopp.luebeckmensawidget.api.model.PriceGroup.Students
import androidx.datastore.preferences.preferencesDataStore
import de.janhopp.luebeckmensawidget.api.model.City
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.DietFilter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "options_storage")

class OptionsStorage(
    context: Context,
) {
    private val dataStore = context.dataStore

    suspend fun getBoolean(option: Option<Boolean>): Boolean {
        return dataStore.data.map { preferences -> preferences[option.toBooleanKey()] }
            .firstOrNull() ?: option.defaultValue
    }

    suspend fun setBoolean(option: Option<Boolean>, value: Boolean) {
        dataStore.edit { preferences -> preferences[option.toBooleanKey()] = value }
    }

    suspend fun getString(option: Option<String>): String {
        return dataStore.data.map { preferences -> preferences[option.toStringKey()] }.firstOrNull()
            ?: option.defaultValue
    }

    suspend fun setString(option: Option<String>, value: String) {
        dataStore.edit { preferences -> preferences[option.toStringKey()] = value }
    }

    suspend fun getStringSet(option: Option<Set<String>>): Set<String> {
        return dataStore.data.map { preferences -> preferences[option.toStringSetKey()] }.firstOrNull()
            ?: option.defaultValue
    }

    suspend fun setStringSet(option: Option<Set<String>>, value: Set<String>) {
        dataStore.edit { preferences -> preferences[option.toStringSetKey()] = value }
    }

    private companion object {
        fun Option<Boolean>.toBooleanKey() = booleanPreferencesKey(key)
        fun Option<String>.toStringKey() = stringPreferencesKey(key)
        fun Option<Set<String>>.toStringSetKey() = stringSetPreferencesKey(key)
    }
}

sealed class Option<T>(
    val key: String,
    val defaultValue: T,
) {
    data object ShowDate : Option<Boolean>(key = "show_date", defaultValue = false)
    data object UseEmoji : Option<Boolean>(key = "use_emoji", defaultValue = true)
    data object PriceGroup : Option<String>(key = "price_group", defaultValue = Students.name)
    data object FilterDeals : Option<Boolean>(key = "filter_deals", defaultValue = false)
    data object MensaCity : Option<String>(key = "city", defaultValue = City.Luebeck.name)
    data object Locations : Option<Set<String>>(key = "locations", defaultValue = setOf(Location.MensaLuebeck.code))
    data object Allergens : Option<Set<String>>(key = "allergens", defaultValue = emptySet())
    data object DietFilter : Option<String>(key = "diet_filter", defaultValue = "none")
}
