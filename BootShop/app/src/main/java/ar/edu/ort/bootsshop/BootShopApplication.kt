package ar.edu.ort.bootsshop

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ar.edu.ort.bootsshop.data.FavouriteRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting"
)

class BootShopApplication: Application() {
    lateinit var favouriteRepository: FavouriteRepository

    override fun onCreate() {
        super.onCreate()
        favouriteRepository = FavouriteRepository(dataStore)
    }

}