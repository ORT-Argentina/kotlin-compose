package ar.edu.ort.frases.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.frases.auth.AuthRepository
import ar.edu.ort.frases.auth.AuthUiState
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun checkSession() {
        _uiState.value = if (authRepository.currentUser != null) {
            AuthUiState.Authenticated
        } else {
            AuthUiState.Unauthenticated
        }
    }

    fun signIn(email: String, password: String) {
        if (!validateEmailAndPassword(email, password)) return
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            authRepository.signInWithEmail(email, password)
                .onSuccess { _uiState.value = AuthUiState.Authenticated }
                .onFailure { _uiState.value = AuthUiState.Error(it.toUserMessage()) }
        }
    }

    fun register(email: String, password: String, confirmPassword: String) {
        if (!validateEmailAndPassword(email, password)) return
        if (password != confirmPassword) {
            _uiState.value = AuthUiState.Error("Las contrasenas no coinciden.")
            return
        }
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            authRepository.registerWithEmail(email, password)
                .onSuccess { _uiState.value = AuthUiState.Authenticated }
                .onFailure { _uiState.value = AuthUiState.Error(it.toUserMessage()) }
        }
    }

    fun sendPasswordReset(email: String) {
        if (email.isBlank() || !email.contains("@")) {
            _uiState.value = AuthUiState.Error("Ingresa un email valido.")
            return
        }
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            authRepository.sendPasswordReset(email)
                .onSuccess { _uiState.value = AuthUiState.PasswordResetSent(email.trim()) }
                .onFailure { _uiState.value = AuthUiState.Error(it.toUserMessage()) }
        }
    }

    fun signInWithGoogleToken(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            authRepository.signInWithGoogle(idToken)
                .onSuccess { _uiState.value = AuthUiState.Authenticated }
                .onFailure { _uiState.value = AuthUiState.Error(it.toUserMessage()) }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _uiState.value = AuthUiState.Unauthenticated
    }

    fun showError(message: String) {
        _uiState.value = AuthUiState.Error(message)
    }

    fun clearMessage() {
        _uiState.value = AuthUiState.Idle
    }

    private fun validateEmailAndPassword(email: String, password: String): Boolean {
        if (email.isBlank() || !email.contains("@")) {
            _uiState.value = AuthUiState.Error("Ingresa un email valido.")
            return false
        }
        if (password.length < 6) {
            _uiState.value = AuthUiState.Error("La contrasena debe tener al menos 6 caracteres.")
            return false
        }
        return true
    }

    private fun Throwable.toUserMessage(): String =
        when (this) {
            is FirebaseNetworkException -> "No se pudo conectar. Revisa tu conexion."
            is FirebaseAuthException -> when (errorCode) {
                "ERROR_EMAIL_ALREADY_IN_USE" -> "Ese email ya esta registrado."
                "ERROR_INVALID_EMAIL" -> "El email no es valido."
                "ERROR_INVALID_CREDENTIAL" -> "Credenciales invalidas."
                "ERROR_WRONG_PASSWORD" -> "Contrasena incorrecta."
                "ERROR_WEAK_PASSWORD" -> "La contrasena es demasiado debil."
                else -> localizedMessage ?: "No se pudo completar la autenticacion."
            }
            else -> localizedMessage ?: "No se pudo completar la autenticacion."
        }
}
