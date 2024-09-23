package ar.edu.ort.bootsshop.screens.detail

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController


@Composable
fun DetailRoute(
    detailViewModel: DetailViewModel,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController
) {


    DetailScreen(
        navController,
        bootItem = detailViewModel.getItem(),
        /*openDrawer = openDrawer,
        snackbarHostState = snackbarHostState,*/
    )
}