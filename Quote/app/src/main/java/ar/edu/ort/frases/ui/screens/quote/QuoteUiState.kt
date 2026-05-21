package ar.edu.ort.frases.ui.screens.quote

import ar.edu.ort.frases.model.Quote

data class QuoteUiState(
    val isLoading: Boolean = false,
    val quote: Quote? = null,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null
)
