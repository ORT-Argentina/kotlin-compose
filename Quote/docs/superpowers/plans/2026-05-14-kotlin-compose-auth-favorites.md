# Kotlin Compose Auth And Favorites Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add Firebase Authentication, Google Sign-In, local Room favorites, Navigation Compose, and Material 3 screen polish to the existing Kotlin Compose quote app.

**Architecture:** Keep the existing single-activity Compose app and Hilt setup. Move feature behavior out of `MainActivity` into navigation destinations, repositories, and `StateFlow` ViewModels. Favorites are local-only through Room; auth is through Firebase Auth.

**Tech Stack:** Kotlin, Jetpack Compose, Material 3, Navigation Compose, Hilt, StateFlow, Retrofit, Room, Firebase Auth, Google Identity/Credential Manager.

---

## File Map

Modify:

- `build.gradle.kts`: add Google Services plugin alias at root.
- `gradle/libs.versions.toml`: add versions and aliases for Firebase, Room, Navigation Compose, Credential Manager, coroutines play services, and Google Services plugin.
- `app/build.gradle.kts`: apply Google Services plugin and add app dependencies.
- `app/src/main/AndroidManifest.xml`: keep Hilt application and ensure internet permission remains available.
- `app/src/main/java/ar/edu/ort/frases/MainActivity.kt`: replace inline quote UI with app navigation host.
- `app/src/main/java/ar/edu/ort/frases/viewmodel/MainViewModel.kt`: replace or retire after introducing `QuoteViewModel`.
- `app/src/main/java/ar/edu/ort/frases/ui/theme/Color.kt`: define app palette.
- `app/src/main/java/ar/edu/ort/frases/ui/theme/Theme.kt`: use new light/dark schemes.

Create:

- `app/src/main/java/ar/edu/ort/frases/auth/AuthUiState.kt`
- `app/src/main/java/ar/edu/ort/frases/auth/AuthRepository.kt`
- `app/src/main/java/ar/edu/ort/frases/auth/FirebaseAuthRepository.kt`
- `app/src/main/java/ar/edu/ort/frases/data/local/FavoriteQuoteEntity.kt`
- `app/src/main/java/ar/edu/ort/frases/data/local/FavoriteQuoteDao.kt`
- `app/src/main/java/ar/edu/ort/frases/data/local/QuoteDatabase.kt`
- `app/src/main/java/ar/edu/ort/frases/data/repository/FavoriteQuoteRepository.kt`
- `app/src/main/java/ar/edu/ort/frases/data/repository/QuoteRepository.kt`
- `app/src/main/java/ar/edu/ort/frases/di/AuthModule.kt`
- `app/src/main/java/ar/edu/ort/frases/di/DatabaseModule.kt`
- `app/src/main/java/ar/edu/ort/frases/di/RepositoryModule.kt`
- `app/src/main/java/ar/edu/ort/frases/navigation/AppDestination.kt`
- `app/src/main/java/ar/edu/ort/frases/navigation/FrasesNavHost.kt`
- `app/src/main/java/ar/edu/ort/frases/ui/screens/login/LoginScreen.kt`
- `app/src/main/java/ar/edu/ort/frases/ui/screens/register/RegisterScreen.kt`
- `app/src/main/java/ar/edu/ort/frases/ui/screens/forgot_password/ForgotPasswordScreen.kt`
- `app/src/main/java/ar/edu/ort/frases/ui/screens/quote/QuoteScreen.kt`
- `app/src/main/java/ar/edu/ort/frases/ui/screens/favorites/FavoritesScreen.kt`
- `app/src/main/java/ar/edu/ort/frases/viewmodel/AuthViewModel.kt`
- `app/src/main/java/ar/edu/ort/frases/viewmodel/QuoteViewModel.kt`
- `app/src/main/java/ar/edu/ort/frases/viewmodel/FavoritesViewModel.kt`
- `app/src/test/java/ar/edu/ort/frases/data/local/FavoriteQuoteEntityTest.kt`

Manual developer file:

- `app/google-services.json`: must be downloaded from Firebase Console and placed by the developer. Do not invent this file.

---

### Task 1: Dependencies And Firebase Build Setup

**Files:**
- Modify: `gradle/libs.versions.toml`
- Modify: `build.gradle.kts`
- Modify: `app/build.gradle.kts`

- [ ] **Step 1: Add dependency aliases**

In `gradle/libs.versions.toml`, add these version entries under `[versions]`:

```toml
firebaseBom = "33.7.0"
googleServices = "4.4.2"
navigationCompose = "2.8.5"
room = "2.6.1"
credentialManager = "1.3.0"
googleId = "1.1.1"
coroutinesPlayServices = "1.9.0"
```

Add these library entries under `[libraries]`:

```toml
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
firebase-auth = { group = "com.google.firebase", name = "firebase-auth" }
androidx-credentials = { group = "androidx.credentials", name = "credentials", version.ref = "credentialManager" }
androidx-credentials-play-services-auth = { group = "androidx.credentials", name = "credentials-play-services-auth", version.ref = "credentialManager" }
googleid = { group = "com.google.android.libraries.identity.googleid", name = "googleid", version.ref = "googleId" }
kotlinx-coroutines-play-services = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-play-services", version.ref = "coroutinesPlayServices" }
```

Add this plugin alias under `[plugins]`:

```toml
google-services = { id = "com.google.gms.google-services", version.ref = "googleServices" }
```

- [ ] **Step 2: Add root plugin alias**

In root `build.gradle.kts`, add:

```kotlin
alias(libs.plugins.google.services) apply false
```

The full plugins block should contain Android application, Kotlin Android, Kotlin Compose, Hilt, and Google Services.

- [ ] **Step 3: Apply app plugin and dependencies**

In `app/build.gradle.kts`, add this plugin:

```kotlin
alias(libs.plugins.google.services)
```

Add dependencies:

```kotlin
implementation(libs.androidx.navigation.compose)

implementation(platform(libs.firebase.bom))
implementation(libs.firebase.auth)
implementation(libs.kotlinx.coroutines.play.services)

implementation(libs.androidx.credentials)
implementation(libs.androidx.credentials.play.services.auth)
implementation(libs.googleid)

implementation(libs.androidx.room.runtime)
implementation(libs.androidx.room.ktx)
kapt(libs.androidx.room.compiler)
```

- [ ] **Step 4: Verify dependency resolution**

Run:

```bash
./gradlew :app:dependencies --configuration debugRuntimeClasspath
```

Expected: command resolves dependencies. If it fails because `google-services.json` is missing after applying the plugin, proceed with implementation but document that the developer must add the file before full builds.

---

### Task 2: Room Favorites Model And Repository

**Files:**
- Create: `app/src/main/java/ar/edu/ort/frases/data/local/FavoriteQuoteEntity.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/data/local/FavoriteQuoteDao.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/data/local/QuoteDatabase.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/data/repository/FavoriteQuoteRepository.kt`
- Create: `app/src/test/java/ar/edu/ort/frases/data/local/FavoriteQuoteEntityTest.kt`

- [ ] **Step 1: Write deterministic ID test**

Create `FavoriteQuoteEntityTest.kt`:

```kotlin
package ar.edu.ort.frases.data.local

import ar.edu.ort.frases.model.Quote
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class FavoriteQuoteEntityTest {

    @Test
    fun sameQuoteAndAuthorProduceSameId() {
        val first = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Unknown", category = "inspire")
        )
        val second = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Unknown", category = "life")
        )

        assertEquals(first.id, second.id)
    }

    @Test
    fun differentAuthorProducesDifferentId() {
        val first = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Unknown", category = "inspire")
        )
        val second = FavoriteQuoteEntity.fromQuote(
            Quote(quote = "Stay hungry", author = "Other", category = "inspire")
        )

        assertNotEquals(first.id, second.id)
    }
}
```

- [ ] **Step 2: Run test and verify it fails**

Run:

```bash
./gradlew :app:testDebugUnitTest --tests "ar.edu.ort.frases.data.local.FavoriteQuoteEntityTest"
```

Expected: FAIL because `FavoriteQuoteEntity` does not exist yet.

- [ ] **Step 3: Implement entity**

Create `FavoriteQuoteEntity.kt`:

```kotlin
package ar.edu.ort.frases.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.ort.frases.model.Quote

@Entity(tableName = "favorite_quotes")
data class FavoriteQuoteEntity(
    @PrimaryKey val id: String,
    val text: String,
    val author: String,
    val category: String
) {
    fun toQuote(): Quote = Quote(
        quote = text,
        author = author,
        category = category
    )

    companion object {
        fun fromQuote(quote: Quote): FavoriteQuoteEntity {
            val normalizedText = quote.quote.trim().lowercase()
            val normalizedAuthor = quote.author.trim().lowercase()
            return FavoriteQuoteEntity(
                id = "$normalizedText|$normalizedAuthor",
                text = quote.quote,
                author = quote.author,
                category = quote.category
            )
        }
    }
}
```

- [ ] **Step 4: Implement DAO**

Create `FavoriteQuoteDao.kt`:

```kotlin
package ar.edu.ort.frases.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(quote: FavoriteQuoteEntity)

    @Delete
    suspend fun deleteFavorite(quote: FavoriteQuoteEntity)

    @Query("DELETE FROM favorite_quotes WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)

    @Query("SELECT * FROM favorite_quotes ORDER BY author ASC, text ASC")
    fun getAllFavorites(): Flow<List<FavoriteQuoteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_quotes WHERE id = :id)")
    fun exists(id: String): Flow<Boolean>
}
```

- [ ] **Step 5: Implement database**

Create `QuoteDatabase.kt`:

```kotlin
package ar.edu.ort.frases.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteQuoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun favoriteQuoteDao(): FavoriteQuoteDao
}
```

- [ ] **Step 6: Implement repository**

Create `FavoriteQuoteRepository.kt`:

```kotlin
package ar.edu.ort.frases.data.repository

import ar.edu.ort.frases.data.local.FavoriteQuoteDao
import ar.edu.ort.frases.data.local.FavoriteQuoteEntity
import ar.edu.ort.frases.model.Quote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteQuoteRepository @Inject constructor(
    private val dao: FavoriteQuoteDao
) {
    fun getAllFavorites(): Flow<List<FavoriteQuoteEntity>> = dao.getAllFavorites()

    fun exists(quote: Quote): Flow<Boolean> = dao.exists(FavoriteQuoteEntity.fromQuote(quote).id)

    suspend fun addFavorite(quote: Quote) {
        dao.insertFavorite(FavoriteQuoteEntity.fromQuote(quote))
    }

    suspend fun removeFavorite(quote: Quote) {
        dao.deleteFavorite(FavoriteQuoteEntity.fromQuote(quote))
    }

    suspend fun removeFavoriteById(id: String) {
        dao.deleteFavoriteById(id)
    }
}
```

- [ ] **Step 7: Verify entity tests pass**

Run:

```bash
./gradlew :app:testDebugUnitTest --tests "ar.edu.ort.frases.data.local.FavoriteQuoteEntityTest"
```

Expected: PASS.

---

### Task 3: Hilt Modules For Firebase, Room, And Repositories

**Files:**
- Create: `app/src/main/java/ar/edu/ort/frases/di/AuthModule.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/di/DatabaseModule.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/di/RepositoryModule.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/auth/AuthRepository.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/auth/FirebaseAuthRepository.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/data/repository/QuoteRepository.kt`

- [ ] **Step 1: Define auth repository contract**

Create `AuthRepository.kt`:

```kotlin
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
```

- [ ] **Step 2: Implement Firebase auth repository**

Create `FirebaseAuthRepository.kt`:

```kotlin
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
```

- [ ] **Step 3: Provide Firebase Auth**

Create `AuthModule.kt`:

```kotlin
package ar.edu.ort.frases.di

import ar.edu.ort.frases.auth.AuthRepository
import ar.edu.ort.frases.auth.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(repository: FirebaseAuthRepository): AuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}
```

- [ ] **Step 4: Provide Room database and DAO**

Create `DatabaseModule.kt`:

```kotlin
package ar.edu.ort.frases.di

import android.content.Context
import androidx.room.Room
import ar.edu.ort.frases.data.local.FavoriteQuoteDao
import ar.edu.ort.frases.data.local.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase =
        Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quotes.db"
        ).build()

    @Provides
    fun provideFavoriteQuoteDao(database: QuoteDatabase): FavoriteQuoteDao =
        database.favoriteQuoteDao()
}
```

- [ ] **Step 5: Add quote repository around current use case**

Create `QuoteRepository.kt`:

```kotlin
package ar.edu.ort.frases.data.repository

import ar.edu.ort.frases.model.Quote
import ar.edu.ort.frases.shared.GetServiceQuotes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepository @Inject constructor(
    private val getServiceQuotes: GetServiceQuotes
) {
    suspend fun getRandomQuote(): Result<Quote> = runCatching {
        val quotes = getServiceQuotes.invoke().orEmpty()
        quotes.firstOrNull() ?: error("No se encontraron frases.")
    }
}
```

- [ ] **Step 6: Verify Hilt compilation**

Run:

```bash
./gradlew :app:kaptDebugKotlin
```

Expected: Hilt and Room annotation processing succeeds, except if Firebase Google Services fails because `google-services.json` is not present. If the file is missing, add it before continuing full verification.

---

### Task 4: Auth ViewModel And Screens

**Files:**
- Create: `app/src/main/java/ar/edu/ort/frases/auth/AuthUiState.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/viewmodel/AuthViewModel.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/ui/screens/login/LoginScreen.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/ui/screens/register/RegisterScreen.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/ui/screens/forgot_password/ForgotPasswordScreen.kt`

- [ ] **Step 1: Add auth UI state**

Create `AuthUiState.kt`:

```kotlin
package ar.edu.ort.frases.auth

sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data object Authenticated : AuthUiState
    data object Unauthenticated : AuthUiState
    data class PasswordResetSent(val email: String) : AuthUiState
    data class Error(val message: String) : AuthUiState
}
```

- [ ] **Step 2: Implement AuthViewModel**

Create `AuthViewModel.kt` with validation, Firebase calls, and Google token login:

```kotlin
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
```

- [ ] **Step 3: Implement login screen**

Create `LoginScreen.kt` with email/password fields, primary login button, Google login button, register link, forgot password link, loading, and error snackbar. The Google button should call a callback `onGoogleSignInClick`.

- [ ] **Step 4: Implement register screen**

Create `RegisterScreen.kt` with email, password, confirm password fields and callbacks:

```kotlin
@Composable
fun RegisterScreen(
    uiState: AuthUiState,
    onRegisterClick: (String, String, String) -> Unit,
    onBackToLoginClick: () -> Unit,
    onClearMessage: () -> Unit
)
```

- [ ] **Step 5: Implement forgot password screen**

Create `ForgotPasswordScreen.kt` with email field and callbacks:

```kotlin
@Composable
fun ForgotPasswordScreen(
    uiState: AuthUiState,
    onSendResetClick: (String) -> Unit,
    onBackToLoginClick: () -> Unit,
    onClearMessage: () -> Unit
)
```

- [ ] **Step 6: Verify Compose compilation**

Run:

```bash
./gradlew :app:compileDebugKotlin
```

Expected: auth ViewModel and screens compile.

---

### Task 5: Quote And Favorites ViewModels

**Files:**
- Create: `app/src/main/java/ar/edu/ort/frases/viewmodel/QuoteViewModel.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/viewmodel/FavoritesViewModel.kt`
- Modify: `app/src/main/java/ar/edu/ort/frases/viewmodel/MainViewModel.kt`

- [ ] **Step 1: Implement QuoteViewModel**

Create `QuoteViewModel.kt`:

```kotlin
package ar.edu.ort.frases.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.frases.data.repository.FavoriteQuoteRepository
import ar.edu.ort.frases.data.repository.QuoteRepository
import ar.edu.ort.frases.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuoteUiState(
    val isLoading: Boolean = false,
    val quote: Quote? = null,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null
)

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
```

- [ ] **Step 2: Implement FavoritesViewModel**

Create `FavoritesViewModel.kt`:

```kotlin
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
```

- [ ] **Step 3: Retire old MainViewModel usage**

Leave `MainViewModel.kt` in place if it still compiles, but stop using it from `MainActivity`. If unused imports or lints appear, remove the file in a later cleanup only after confirming no references remain.

- [ ] **Step 4: Verify ViewModel compilation**

Run:

```bash
./gradlew :app:compileDebugKotlin
```

Expected: new ViewModels compile.

---

### Task 6: Navigation And Google Sign-In Wiring

**Files:**
- Create: `app/src/main/java/ar/edu/ort/frases/navigation/AppDestination.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/navigation/FrasesNavHost.kt`
- Modify: `app/src/main/java/ar/edu/ort/frases/MainActivity.kt`

- [ ] **Step 1: Define destinations**

Create `AppDestination.kt`:

```kotlin
package ar.edu.ort.frases.navigation

sealed class AppDestination(val route: String) {
    data object Login : AppDestination("login")
    data object Register : AppDestination("register")
    data object ForgotPassword : AppDestination("forgot_password")
    data object Quote : AppDestination("quote")
    data object Favorites : AppDestination("favorites")
}
```

- [ ] **Step 2: Implement navigation host**

Create `FrasesNavHost.kt`. It should:

- Use `rememberNavController()`.
- Start at `login`.
- Collect `AuthViewModel.uiState`.
- Navigate to `quote` on `Authenticated`, clearing auth back stack.
- Navigate to `login` on logout, clearing main back stack.
- Use `hiltViewModel()` for auth, quote, and favorites destinations.
- Trigger Google Sign-In from `LoginScreen` and pass the returned ID token to `AuthViewModel.signInWithGoogleToken`.

The Google flow should use Credential Manager with `GetGoogleIdOption`. The Web Client ID must come from a string resource generated by Firebase/Google Services, usually `R.string.default_web_client_id`.

- [ ] **Step 3: Replace MainActivity content**

Modify `MainActivity.kt` to:

```kotlin
package ar.edu.ort.frases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ar.edu.ort.frases.navigation.FrasesNavHost
import ar.edu.ort.frases.ui.theme.FrasesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FrasesTheme {
                FrasesNavHost()
            }
        }
    }
}
```

- [ ] **Step 4: Verify navigation compilation**

Run:

```bash
./gradlew :app:compileDebugKotlin
```

Expected: `MainActivity` and navigation compile. If `default_web_client_id` is unresolved, add `google-services.json` and rerun.

---

### Task 7: Quote And Favorites Screens

**Files:**
- Create: `app/src/main/java/ar/edu/ort/frases/ui/screens/quote/QuoteScreen.kt`
- Create: `app/src/main/java/ar/edu/ort/frases/ui/screens/favorites/FavoritesScreen.kt`

- [ ] **Step 1: Implement quote screen**

Create `QuoteScreen.kt` with:

```kotlin
@Composable
fun QuoteScreen(
    uiState: QuoteUiState,
    onReloadClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onLogoutClick: () -> Unit
)
```

Screen behavior:

- `Scaffold` with `TopAppBar`.
- Favorites navigation icon.
- Logout action.
- Loading indicator when `uiState.isLoading`.
- Error text and retry button when `uiState.errorMessage != null`.
- Quote card with large typography when `uiState.quote != null`.
- Favorite icon uses filled/outlined visual state through text icon or built-in Material icon dependency if added.

- [ ] **Step 2: Implement favorites screen**

Create `FavoritesScreen.kt` with:

```kotlin
@Composable
fun FavoritesScreen(
    favorites: List<FavoriteQuoteEntity>,
    onBackClick: () -> Unit,
    onDeleteClick: (String) -> Unit
)
```

Screen behavior:

- `Scaffold` with back navigation.
- Empty state when `favorites.isEmpty()`.
- `LazyColumn` with cards.
- Delete icon/action per card.
- `animateItem()` where available; otherwise use stable keys and simple card transitions.

- [ ] **Step 3: Wire screens in navigation**

Update `FrasesNavHost.kt` so the quote destination passes `QuoteViewModel.uiState` to `QuoteScreen`, and favorites destination passes `FavoritesViewModel.favorites` to `FavoritesScreen`.

- [ ] **Step 4: Verify screen compilation**

Run:

```bash
./gradlew :app:compileDebugKotlin
```

Expected: screens and navigation compile.

---

### Task 8: Material 3 Theme Polish

**Files:**
- Modify: `app/src/main/java/ar/edu/ort/frases/ui/theme/Color.kt`
- Modify: `app/src/main/java/ar/edu/ort/frases/ui/theme/Theme.kt`
- Modify: screen files from Tasks 4 and 7 as needed.

- [ ] **Step 1: Replace default colors**

Use a warm quote-focused palette in `Color.kt`:

```kotlin
package ar.edu.ort.frases.ui.theme

import androidx.compose.ui.graphics.Color

val InkBlue = Color(0xFF243447)
val SageGreen = Color(0xFF5F7D6B)
val WarmIvory = Color(0xFFFFFBF2)
val SoftGold = Color(0xFFE0A84F)
val DeepCharcoal = Color(0xFF161A1D)
val NightSurface = Color(0xFF20262B)
val MistBlue = Color(0xFFB8D8D8)
```

- [ ] **Step 2: Update schemes**

Use these colors in `Theme.kt`:

```kotlin
private val DarkColorScheme = darkColorScheme(
    primary = MistBlue,
    secondary = SoftGold,
    tertiary = SageGreen,
    background = DeepCharcoal,
    surface = NightSurface
)

private val LightColorScheme = lightColorScheme(
    primary = InkBlue,
    secondary = SageGreen,
    tertiary = SoftGold,
    background = WarmIvory,
    surface = Color.White
)
```

- [ ] **Step 3: Verify visual consistency manually**

Run the app and check:

- Login fields are readable in light and dark mode.
- Quote remains the dominant visual element.
- Favorite cards have good contrast.
- Buttons are prominent and accessible.

---

### Task 9: Final Verification

**Files:**
- All modified implementation files.

- [ ] **Step 1: Run unit tests**

Run:

```bash
./gradlew :app:testDebugUnitTest
```

Expected: unit tests pass.

- [ ] **Step 2: Run debug assemble**

Run:

```bash
./gradlew :app:assembleDebug
```

Expected: debug APK builds after `app/google-services.json` is present.

- [ ] **Step 3: Manual Firebase configuration check**

Confirm with the developer:

- `app/google-services.json` exists.
- Email/Password provider is enabled in Firebase Console.
- Google provider is enabled in Firebase Console.
- SHA-1 and SHA-256 are configured for the Android app.

- [ ] **Step 4: Manual app flow**

On an emulator/device:

- Register with email/password.
- Logout.
- Login with email/password.
- Request password reset.
- Login with Google.
- Close and reopen app; confirm active session opens quote screen.
- Load a quote.
- Save quote as favorite.
- Navigate to favorites.
- Delete favorite.
- Close and reopen app; confirm remaining favorites persist.

