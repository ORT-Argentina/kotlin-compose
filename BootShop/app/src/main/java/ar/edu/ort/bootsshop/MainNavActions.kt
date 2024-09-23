package ar.edu.ort.bootsshop

import androidx.compose.material3.DrawerState
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppDestinations {
    const val LIST_ITEMS_ROUTE = "list"
    const val DETAILS_ROUTE = "detail/{bootId}"
    const val FAVOURITES = "favourites"
}

class MainNavActions(
    navController: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState
) {
    val navigateToList: () -> Unit = {
        navController.navigate(AppDestinations.LIST_ITEMS_ROUTE) {
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
        navController.navigate(AppDestinations.DETAILS_ROUTE.replace(
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
        navController.navigate(AppDestinations.FAVOURITES) {
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
        scope.launch {
            drawerState.close()
            snackbarHostState.showSnackbar("Profile")
        }
    }
    val navigateToSettings: () -> Unit = {
        scope.launch {
            drawerState.close()
            snackbarHostState.showSnackbar("Settings")
        }
    }
}