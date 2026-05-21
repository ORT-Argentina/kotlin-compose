package ar.edu.ort.frases.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.NoCredentialException
import ar.edu.ort.frases.R
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import kotlinx.coroutines.launch

@Composable
fun rememberGoogleSignInLauncher(
    onTokenReceived: (String) -> Unit,
    onError: (String) -> Unit
): () -> Unit {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    return {
        coroutineScope.launch {
            runCatching {
                val credentialManager = CredentialManager.create(context)
                val signInWithGoogleOption = GetSignInWithGoogleOption.Builder(
                    serverClientId = context.getString(R.string.default_web_client_id)
                )
                    .build()
                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(signInWithGoogleOption)
                    .build()
                val result = credentialManager.getCredential(context, request)
                val credential = result.credential
                GoogleIdTokenCredential.createFrom(credential.data).idToken
            }.onSuccess(onTokenReceived)
                .onFailure { error ->
                    val message = if (error is NoCredentialException) {
                        "No se encontraron credenciales de Google. Revisa que el dispositivo tenga Google Play Services y una cuenta Google configurada."
                    } else {
                        error.localizedMessage ?: "No se pudo iniciar sesion con Google."
                    }
                    onError(message)
                }
        }
    }
}
