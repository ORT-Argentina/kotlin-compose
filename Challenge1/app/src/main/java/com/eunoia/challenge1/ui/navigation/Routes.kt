package com.eunoia.challenge1.ui.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Login   : Screen("login")
    data object Register: Screen("register")
}