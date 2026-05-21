package ar.edu.ort.frases.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.frases.auth.AuthUiState
import ar.edu.ort.frases.auth.rememberGoogleSignInLauncher
import ar.edu.ort.frases.ui.screens.favorites.FavoritesScreen
import ar.edu.ort.frases.ui.screens.forgot_password.ForgotPasswordScreen
import ar.edu.ort.frases.ui.screens.login.LoginScreen
import ar.edu.ort.frases.ui.screens.quote.QuoteScreen
import ar.edu.ort.frases.ui.screens.register.RegisterScreen
import ar.edu.ort.frases.viewmodel.AuthViewModel
import ar.edu.ort.frases.viewmodel.FavoritesViewModel
import ar.edu.ort.frases.viewmodel.QuoteViewModel

@Composable
fun FrasesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    // ViewModel compartido por el grafo para decidir el flujo login/app.
    val authViewModel: AuthViewModel = hiltViewModel()

    // Estado observable de autenticacion; dispara redirecciones y pinta pantallas auth.
    val authUiState by authViewModel.uiState.collectAsState()

    // Entrada actual del back stack para evitar navegar de nuevo al mismo destino.
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    // Ruta actual usada por los efectos de navegacion segun la sesion.
    val currentRoute = currentBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        authViewModel.checkSession()
    }

    LaunchedEffect(authUiState, currentRoute) {
        when (authUiState) {
            AuthUiState.Authenticated -> {
                if (currentRoute != AppDestination.Quote.route && currentRoute != AppDestination.Favorites.route) {
                    navController.navigate(AppDestination.Quote.route) {
                        popUpTo(AppDestination.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
            AuthUiState.Unauthenticated -> {
                if (currentRoute == AppDestination.Quote.route || currentRoute == AppDestination.Favorites.route) {
                    navController.navigate(AppDestination.Login.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
            else -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestination.Login.route,
        modifier = modifier
    ) {
        composable(AppDestination.Login.route) {
            // Accion exclusiva del login: obtiene el token de Google y lo delega al ViewModel.
            val googleSignInLauncher = rememberGoogleSignInLauncher(
                onTokenReceived = authViewModel::signInWithGoogleToken,
                onError = authViewModel::showError
            )

            LoginScreen(
                uiState = authUiState,
                onLoginClick = authViewModel::signIn,
                onGoogleSignInClick = googleSignInLauncher,
                onRegisterClick = { navController.navigate(AppDestination.Register.route) },
                onForgotPasswordClick = { navController.navigate(AppDestination.ForgotPassword.route) },
                onClearMessage = authViewModel::clearMessage
            )
        }
        composable(AppDestination.Register.route) {
            RegisterScreen(
                uiState = authUiState,
                onRegisterClick = authViewModel::register,
                onBackToLoginClick = { navController.popBackStack() },
                onClearMessage = authViewModel::clearMessage
            )
        }
        composable(AppDestination.ForgotPassword.route) {
            ForgotPasswordScreen(
                uiState = authUiState,
                onSendResetClick = authViewModel::sendPasswordReset,
                onBackToLoginClick = { navController.popBackStack() },
                onClearMessage = authViewModel::clearMessage
            )
        }
        composable(AppDestination.Quote.route) {
            val quoteViewModel: QuoteViewModel = hiltViewModel()
            val quoteUiState by quoteViewModel.uiState.collectAsState()
            QuoteScreen(
                uiState = quoteUiState,
                onReloadClick = quoteViewModel::loadQuote,
                onFavoriteClick = quoteViewModel::toggleFavorite,
                onFavoritesClick = { navController.navigate(AppDestination.Favorites.route) },
                onLogoutClick = authViewModel::signOut
            )
        }
        composable(AppDestination.Favorites.route) {
            val favoritesViewModel: FavoritesViewModel = hiltViewModel()
            val favorites by favoritesViewModel.favorites.collectAsState()
            FavoritesScreen(
                favorites = favorites,
                onBackClick = { navController.popBackStack() },
                onDeleteClick = favoritesViewModel::removeFavorite
            )
        }
    }
}
