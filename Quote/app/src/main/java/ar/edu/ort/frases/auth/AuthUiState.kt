package ar.edu.ort.frases.auth

sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data object Authenticated : AuthUiState
    data object Unauthenticated : AuthUiState
    data class PasswordResetSent(val email: String) : AuthUiState
    data class Error(val message: String) : AuthUiState
}
