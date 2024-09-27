package ar.edu.ort.bootsshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ar.edu.ort.bootsshop.data.FavouriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.*
import kotlinx.serialization.json.*


data class UiState (
    val userName: String
)

class MainActivityViewModel(private var favouriteRepository: FavouriteRepository) : ViewModel() {
    private var _titleBar = MutableLiveData("Shop List")
    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    val titleBar: LiveData<String>
        get() = _titleBar

    fun setTitleBar(newTitle: String) {
        _titleBar.value = newTitle
    }

    fun addFavourite(bootId: Int) {
        favouriteRepository.getFavourites.map { favourites ->
            val listFavourites = Json.decodeFromString<MutableList<Int>>(favourites)
            listFavourites.add(bootId)
            val jsonString = Json.encodeToString(listFavourites)
            favouriteRepository.saveFavourites(jsonString)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BootShopApplication)
                MainActivityViewModel(application.favouriteRepository)
            }
        }

        /*fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = (this[APPLICATION_KEY] as BootShopApplication)
                return MainActivityViewModel(application.favouriteRepository) as T
            }
        }*/
    }
}
