package ar.edu.ort.bootsshop

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.data.BootListItems
import ar.edu.ort.bootsshop.screens.detail.DetailRoute
import ar.edu.ort.bootsshop.screens.detail.DetailViewModel
import ar.edu.ort.bootsshop.screens.favourite.FavouriteRoute
import ar.edu.ort.bootsshop.screens.favourite.FavouriteViewModel
import ar.edu.ort.bootsshop.screens.list.ListRoute
import ar.edu.ort.bootsshop.screens.list.ListViewModel

@Composable
fun MainRouteNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainActivityViewModel,
    openDrawer: () -> Unit = {},
    startDestination: String = AppDestinations.LIST_ITEMS_ROUTE,
    navigationActions: MainNavActions,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = AppDestinations.LIST_ITEMS_ROUTE,
        ) {
            viewModel.setTitleBar(stringResource(R.string.list_title_bar))

            val listViewModel: ListViewModel = viewModel(
                factory = ListViewModel.provideFactory()
            )

            listViewModel.setItems(BootListItems)

            ListRoute(
                listViewModel = listViewModel,
                openDrawer = openDrawer,
                navigationActions = navigationActions
            )
        }
        composable(AppDestinations.DETAILS_ROUTE) { backStackEntry ->
            viewModel.setTitleBar(stringResource(R.string.detail_title_bar))
            val bootId = backStackEntry.arguments?.getString("bootId")

            val viewModel: DetailViewModel = viewModel(
                factory = DetailViewModel.provideFactory()
            )

            BootListItems.find { it.id == bootId?.toInt() }?.let {
                viewModel.setItem(it)
            }

            DetailRoute(
                navController = navController,
                detailViewModel = viewModel,
                openDrawer = openDrawer
            )
        }
        composable(AppDestinations.FAVOURITES) { backStackEntry ->
            viewModel.setTitleBar(stringResource(R.string.favorite_title_bar))

            val viewModel: FavouriteViewModel = viewModel(
                factory = FavouriteViewModel.provideFactory()
            )

            FavouriteRoute(
                navController = navController,
                navigationActions = navigationActions,
                favouriteViewModel = viewModel,
                openDrawer = openDrawer
            )
        }
    }
}