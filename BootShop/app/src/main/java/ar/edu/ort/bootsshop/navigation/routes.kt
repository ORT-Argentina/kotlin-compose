package ar.edu.ort.bootsshop.navigation

sealed class RootScreen(val route: String) {
    object Home : RootScreen("list_root")
    object Search : RootScreen("search_root")
    object Favorites : RootScreen("favourite_root")
    object Profile : RootScreen("profile_root")
    object Setting : RootScreen("setting_root")
    object Cart : RootScreen("cart_root")
}

sealed class LeafScreen(val route: String) {
    object Home : LeafScreen("home")
    object Search : LeafScreen("search")
    object Favorites : LeafScreen("favorites")
    object Profile : LeafScreen("profile")
    object Setting : LeafScreen("setting")
    object ItemDetail : LeafScreen("detail/{bootId}")
}