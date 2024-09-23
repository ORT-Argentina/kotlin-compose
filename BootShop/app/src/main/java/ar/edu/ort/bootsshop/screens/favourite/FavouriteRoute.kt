package ar.edu.ort.bootsshop.screens.favourite

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import ar.edu.ort.bootsshop.MainNavActions

@Composable
fun FavouriteRoute(
    favouriteViewModel: FavouriteViewModel,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController,
    navigationActions: MainNavActions
) {
    FavouriteScreen(
        navController,
        favouriteViewModel,
        navigationActions,
        openDrawer,
        snackbarHostState
    )
}