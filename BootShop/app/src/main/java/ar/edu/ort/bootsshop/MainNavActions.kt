package ar.edu.ort.bootsshop

import androidx.compose.material3.DrawerState
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import ar.edu.ort.bootsshop.navigation.LeafScreen
import ar.edu.ort.bootsshop.navigation.RootScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppDestinations {
    const val LIST_ITEMS_ROUTE = "list"
    const val DETAILS_ROUTE = "detail/{bootId}"
    const val FAVOURITES = "favourite"
    const val PROFILE = "profile"
}

class MainNavActions(
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val navigateToList: () -> Unit = {
        navController.navigate(RootScreen.Home.route) {
            scope.launch {
                drawerState.close()
            }
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToDetail: (bootID: Int) -> Unit = { bootID ->
        navController.navigate(LeafScreen.ItemDetail.route.replace(
            oldValue = "{bootId}",
            newValue = bootID.toString()
        )) {
            scope.launch {
                drawerState.close()
            }
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToFavorite: () -> Unit = {
        navController.navigate(LeafScreen.Favorites.route) {
            scope.launch {
                drawerState.close()
            }
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToProfile: () -> Unit = {
        navController.navigate(LeafScreen.Profile.route) {
            scope.launch {
                drawerState.close()
            }
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(LeafScreen.Setting.route) {
            scope.launch {
                drawerState.close()
            }
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}