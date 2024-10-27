package ar.edu.ort.bootsshop

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ar.edu.ort.bootsshop.data.FavouriteRepository
import ar.edu.ort.bootsshop.data.SettingRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting"
)

class BootShopApplication: Application() {
    lateinit var favouriteRepository: FavouriteRepository
    lateinit var settingRepository: SettingRepository

    override fun onCreate() {
        super.onCreate()
        favouriteRepository = FavouriteRepository(dataStore)
        settingRepository = SettingRepository(dataStore)
    }

}