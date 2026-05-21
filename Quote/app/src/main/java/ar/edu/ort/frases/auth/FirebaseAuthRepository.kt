package ar.edu.ort.frases.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun signInWithEmail(email: String, password: String): Result<FirebaseUser> =
        runCatching {
            firebaseAuth.signInWithEmailAndPassword(email.trim(), password).await().user
                ?: error("No se pudo obtener el usuario autenticado.")
        }

    override suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser> =
        runCatching {
            firebaseAuth.createUserWithEmailAndPassword(email.trim(), password).await().user
                ?: error("No se pudo crear el usuario.")
        }

    override suspend fun sendPasswordReset(email: String): Result<Unit> =
        runCatching {
            firebaseAuth.sendPasswordResetEmail(email.trim()).await()
        }

    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser> =
        runCatching {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential).await().user
                ?: error("No se pudo autenticar con Google.")
        }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}
