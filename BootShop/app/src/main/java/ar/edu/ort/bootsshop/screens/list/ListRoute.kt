package ar.edu.ort.bootsshop.screens.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.ort.bootsshop.MainNavActions

@Composable
fun ListRoute(
    listViewModel: ListViewModel,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navigationActions: MainNavActions
) {

    val uiState by listViewModel.uiState.collectAsStateWithLifecycle()

    ListRoute(
        listViewModel = listViewModel,
        uiState = uiState,
        onToggleFavorite = { listViewModel.toggleFavourite(it) },
        openDrawer = openDrawer,
        snackbarHostState = snackbarHostState,
        navigationActions = navigationActions
    )
}

@Composable
fun ListRoute(
    listViewModel: ListViewModel,
    uiState: ListUiState,
    onToggleFavorite: (String) -> Unit,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
    navigationActions: MainNavActions
) {

    listViewModel.getItems()?.let { ListScreen(it, navigationActions, PaddingValues(16.dp)) }
}