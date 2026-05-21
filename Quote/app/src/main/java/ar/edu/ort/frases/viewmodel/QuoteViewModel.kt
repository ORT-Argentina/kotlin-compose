package ar.edu.ort.frases.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.frases.data.repository.FavoriteQuoteRepository
import ar.edu.ort.frases.data.repository.QuoteRepository
import ar.edu.ort.frases.model.Quote
import ar.edu.ort.frases.ui.screens.quote.QuoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val favoriteQuoteRepository: FavoriteQuoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuoteUiState(isLoading = true))
    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()

    private var favoriteObserver: Job? = null

    init {
        loadQuote()
    }

    fun loadQuote() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            quoteRepository.getRandomQuote()
                .onSuccess { quote ->
                    _uiState.update { it.copy(isLoading = false, quote = quote) }
                    observeFavorite(quote)
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "No se pudo cargar la frase."
                        )
                    }
                }
        }
    }

    fun toggleFavorite() {
        val quote = _uiState.value.quote ?: return
        viewModelScope.launch {
            if (_uiState.value.isFavorite) {
                favoriteQuoteRepository.removeFavorite(quote)
            } else {
                favoriteQuoteRepository.addFavorite(quote)
            }
        }
    }

    private fun observeFavorite(quote: Quote) {
        favoriteObserver?.cancel()
        favoriteObserver = viewModelScope.launch {
            favoriteQuoteRepository.exists(quote).collectLatest { exists ->
                _uiState.update { it.copy(isFavorite = exists) }
            }
        }
    }
}
