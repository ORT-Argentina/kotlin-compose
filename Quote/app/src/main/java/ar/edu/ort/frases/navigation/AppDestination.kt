package ar.edu.ort.frases.navigation

sealed class AppDestination(val route: String) {
    data object Login : AppDestination("login")
    data object Register : AppDestination("register")
    data object ForgotPassword : AppDestination("forgot_password")
    data object Quote : AppDestination("quote")
    data object Favorites : AppDestination("favorites")
}
