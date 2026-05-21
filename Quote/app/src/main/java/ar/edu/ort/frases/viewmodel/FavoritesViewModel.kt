package ar.edu.ort.frases.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.frases.data.local.FavoriteQuoteEntity
import ar.edu.ort.frases.data.repository.FavoriteQuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteQuoteRepository: FavoriteQuoteRepository
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteQuoteEntity>> =
        favoriteQuoteRepository.getAllFavorites()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            favoriteQuoteRepository.removeFavoriteById(id)
        }
    }
}
