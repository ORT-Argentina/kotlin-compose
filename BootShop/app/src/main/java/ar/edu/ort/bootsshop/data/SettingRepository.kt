package ar.edu.ort.bootsshop.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

const val IS_DYNAMIC_THEME = "is_dynamic_theme"
const val THEME_TYPE = "theme_type"

enum class ThemeType { SYSTEM, LIGHT, DARK }

class SettingRepository (private val dataStore: DataStore<Preferences>) {
    private companion object {
        val isDynamicTheme = booleanPreferencesKey(IS_DYNAMIC_THEME)
        val themeType = intPreferencesKey(THEME_TYPE)
    }

    val isDynamicThemeFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[isDynamicTheme] ?: false
    }

    val themeTypeFlow: Flow<ThemeType> = dataStore.data.map { preferences ->
        ThemeType.entries[preferences[themeType] ?: 0]
    }

    fun setDynamicTheme(value: Boolean, scope: CoroutineScope) {
        scope.launch {
            dataStore.edit { preferences ->
                preferences[isDynamicTheme] = value
            }
        }
    }

    fun setThemeType(value: ThemeType, scope: CoroutineScope) {
        scope.launch {
            dataStore.edit { preferences ->
                preferences[themeType] = value.ordinal
            }
        }
    }
}