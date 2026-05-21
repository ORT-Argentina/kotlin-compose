package ar.edu.ort.frases.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signInWithEmail(email: String, password: String): Result<FirebaseUser>
    suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser>
    suspend fun sendPasswordReset(email: String): Result<Unit>
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>
    fun signOut()
}
