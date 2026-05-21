# Kotlin Compose Auth And Favorites Design

## Context

The current Android app is a small Kotlin + Jetpack Compose project that shows one quote fetched through Retrofit. It already uses Hilt, a `MainViewModel`, Material 3 theme scaffolding, and a simple API/domain model:

- `Quote(quote, author, category)`
- `MainActivity` currently owns the only screen and calls `MainViewModel.loadQuotes()`
- State is currently exposed through Compose `mutableStateOf`
- There is no Navigation Compose graph, Firebase, Room, or StateFlow yet

This design evolves the app conservatively: keep Kotlin, Compose, Hilt, Retrofit, and the current package as the base, while adding authentication, navigation, local favorites, and a more polished UI.

## Scope

In scope:

- Firebase Authentication as the app entry point.
- Login with email/password.
- Login with Google Sign-In.
- Registration with email, password, and password confirmation.
- Password recovery by email.
- Session check on startup.
- Logout from the main app.
- Local favorites with Room, stored per device.
- Navigation Compose for auth, quote, and favorites screens.
- Material 3 UI improvements with light and dark themes.
- ViewModels exposing UI state through `StateFlow`.

Out of scope for this iteration:

- Firestore or cross-device favorites sync.
- User profile editing beyond logout.
- XML layouts or Java code.
- Migrating the quote API layer beyond what is needed for clean screen state.

## Recommended Approach

Use the approved "modular conservative" approach.

`MainActivity` becomes a lightweight Compose host. It applies `FrasesTheme`, creates the app-level navigation graph, and does not own feature state directly.

Suggested package layout:

```text
ar.edu.ort.frases
  auth/
  data/local/
  data/repository/
  di/
  navigation/
  ui/screens/login/
  ui/screens/register/
  ui/screens/forgot_password/
  ui/screens/quote/
  ui/screens/favorites/
  ui/theme/
  viewmodel/
```

Existing classes can move gradually only when helpful. The implementation should avoid broad renames unless they directly support the new features.

## Authentication Design

Firebase Auth will be wrapped by an `AuthRepository` injected through Hilt. `AuthViewModel` exposes a `StateFlow<AuthUiState>` and functions for:

- Checking the current session.
- Signing in with email and password.
- Registering with email and password.
- Sending password reset email.
- Signing in with Google credentials.
- Signing out.
- Clearing transient error messages.

The login flow starts as the initial route. On startup, the app checks `FirebaseAuth.currentUser`:

- If a user exists, navigate to the quote screen.
- If no user exists, show the login screen.

UI states:

```kotlin
sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data object Authenticated : AuthUiState
    data object Unauthenticated : AuthUiState
    data class Error(val message: String) : AuthUiState
}
```

Firebase setup requirements:

- Add the Google Services Gradle plugin.
- Add Firebase BOM and Firebase Auth dependencies.
- Add Google Identity / Credential Manager dependencies for Google Sign-In.
- Add `google-services.json` under `app/`. This file is provided by the developer from Firebase Console.
- Enable Email/Password and Google providers in Firebase Console.
- Configure the Android app SHA-1/SHA-256 in Firebase for Google Sign-In.

## Favorites Design

Favorites are local per device using Room.

Room components:

- `FavoriteQuoteEntity`
- `FavoriteQuoteDao`
- `QuoteDatabase`
- `FavoriteQuoteRepository`

Entity:

```kotlin
@Entity(tableName = "favorite_quotes")
data class FavoriteQuoteEntity(
    @PrimaryKey val id: String,
    val text: String,
    val author: String,
    val category: String
)
```

The `id` should be deterministic, based on quote text and author. This prevents duplicates when the same quote appears again from the API.

DAO operations:

- `insertFavorite(quote)`
- `deleteFavorite(quote)`
- `getAllFavorites(): Flow<List<FavoriteQuoteEntity>>`
- `exists(id): Flow<Boolean>`

`FavoritesViewModel` exposes all favorites as a `StateFlow`. `QuoteViewModel` checks whether the current quote is already saved and exposes that boolean in its screen state.

## Quote Screen Design

The quote screen remains the app's main content but gets a clearer state model:

```kotlin
data class QuoteUiState(
    val isLoading: Boolean = false,
    val quote: Quote? = null,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null
)
```

Actions:

- Load quote.
- Reload another quote.
- Toggle current quote as favorite.
- Navigate to favorites.
- Sign out.

The screen should make the quote visually dominant. Author and category are secondary. Favorite and navigation actions should be visible without competing with the quote.

## Navigation Design

Add Navigation Compose and define stable routes:

- `login`
- `register`
- `forgot_password`
- `quote`
- `favorites`

Flow:

```text
Login/Register/Forgot Password -> Quote -> Favorites
                              \-> Logout -> Login
```

Navigation should avoid leaving auth screens in the back stack after successful login. Logout should clear the main stack and return to login.

`hiltViewModel()` should be used inside destinations so each screen receives the correct ViewModel instance.

## UI/UX Design

Use Material 3 consistently:

- `OutlinedTextField` for auth inputs.
- Prominent primary buttons for login/register actions.
- `Snackbar` or inline supporting text for errors.
- Loading indicators during network/auth operations.
- Cards for favorite quotes.
- Empty state for favorites with icon and clear message.
- Smooth transitions between navigation destinations where the Compose Navigation version supports them.

Theme:

- Replace the default purple-only theme with a quote-friendly palette.
- Keep light and dark color schemes.
- Use Material typography roles intentionally: large text for quotes, title roles for screen headings, body roles for metadata and helper text.

## Error Handling

Auth errors should be translated into clear user-facing Spanish messages:

- Email invalido.
- Contrasena incorrecta o credenciales invalidas.
- Email ya registrado.
- Contrasena debil.
- Red no disponible.
- Inicio con Google cancelado o fallido.

Quote loading should show loading and error states rather than leaving the default "Cargando...." text indefinitely.

Room operations should be launched from ViewModels and should not block the UI thread.

## Testing And Verification

Implementation should be verified with:

- Gradle build: `./gradlew build` or at minimum `./gradlew :app:assembleDebug`.
- Manual auth flow:
  - Email login.
  - Registration.
  - Password reset.
  - Google Sign-In.
  - Existing session redirects to quote screen.
  - Logout returns to login.
- Manual favorites flow:
  - Add current quote to favorites.
  - Favorite icon updates.
  - Favorites list persists after app restart.
  - Delete favorite from list.
  - Empty state appears when list is empty.

Add focused unit tests for deterministic favorite IDs and repository behavior when the implementation introduces those units.

## Risks And Notes

- Google Sign-In requires Firebase Console configuration and SHA fingerprints. Code alone is not enough.
- `google-services.json` should not be invented by the implementation. The developer must provide it.
- The current app stores an API key in resources. This design does not solve that problem, but implementation should avoid adding new secrets to source control.
- The project already uses Hilt. New Firebase, Room, and repository dependencies should follow the same Hilt module style.
- Favorites are intentionally local-only for this iteration.
