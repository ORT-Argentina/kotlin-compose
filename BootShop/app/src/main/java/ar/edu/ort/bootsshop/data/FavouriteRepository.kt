package ar.edu.ort.bootsshop.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteRepository(private val dataStore: DataStore<Preferences>) {
    private companion object {
        val FAVORITES = stringPreferencesKey("store_favourites")
    }

    val getFavourites: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[FAVORITES] ?: "[]"
        }

    suspend fun saveFavourites(serializeList: String) {
        dataStore.edit { preferences ->
            preferences[FAVORITES] = serializeList
        }
    }
}