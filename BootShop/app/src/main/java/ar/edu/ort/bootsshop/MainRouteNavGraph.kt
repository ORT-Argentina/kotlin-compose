package ar.edu.ort.bootsshop

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ar.edu.ort.bootsshop.data.BootListItems
import ar.edu.ort.bootsshop.navigation.LeafScreen
import ar.edu.ort.bootsshop.navigation.RootScreen
import ar.edu.ort.bootsshop.screens.detail.DetailRoute
import ar.edu.ort.bootsshop.screens.detail.DetailViewModel
import ar.edu.ort.bootsshop.screens.favourite.FavouriteRoute
import ar.edu.ort.bootsshop.screens.favourite.FavouriteViewModel
import ar.edu.ort.bootsshop.screens.list.ListRoute
import ar.edu.ort.bootsshop.screens.list.ListViewModel
import ar.edu.ort.bootsshop.screens.profile.ProfileScreen

@Composable
fun MainRouteNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainActivityViewModel,
    openDrawer: () -> Unit = {},
    startDestination: String = RootScreen.Home.route,
    navigationActions: MainNavActions,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        addHomeRoute(navController, navigationActions, viewModel, openDrawer)
        addProfileRoute(navController, navigationActions)
        addSettingRoute(navController, navigationActions)
        addFavoriteRoute(navController, navigationActions, openDrawer)
        /*composable(AppDestinations.FAVOURITES) { backStackEntry ->

        }*/
    }
}

//home navigation
private fun NavGraphBuilder.addHomeRoute(
    navController: NavController,
    navigationActions: MainNavActions,
    viewModel: MainActivityViewModel,
    openDrawer: () -> Unit
) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showIemList(navController, navigationActions, viewModel)
        showHomeDetail(navController, openDrawer)
    }
}

private fun NavGraphBuilder.showHomeDetail(navController: NavController, openDrawer: () -> Unit) {
    composable(route = LeafScreen.ItemDetail.route) { backStackEntry ->
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
}

private fun NavGraphBuilder.showIemList(
    navController: NavController,
    navigationActions: MainNavActions,
    viewModel: MainActivityViewModel
) {
    composable(route = LeafScreen.Home.route) {
        //viewModel.setTitleBar(stringResource(R.string.list_title_bar))
        val listViewModel: ListViewModel = viewModel(
            factory = ListViewModel.provideFactory()
        )

        listViewModel.setItems(BootListItems)

        ListRoute(
            listViewModel = listViewModel,
            navigationActions = navigationActions
        )
    }
}

private fun NavGraphBuilder.addProfileRoute(
    navController: NavController,
    navigationActions: MainNavActions
) {
    navigation(
        route = RootScreen.Profile.route,
        startDestination = LeafScreen.Profile.route
    ) {
        showProfile(navController, navigationActions)
    }
}

private fun NavGraphBuilder.showProfile(
    navController: NavController,
    navigationActions: MainNavActions
) {
    composable(route = LeafScreen.Profile.route) {
        ProfileScreen(navigationActions)
    }
}

private fun NavGraphBuilder.addSettingRoute(
    navController: NavController,
    navigationActions: MainNavActions
) {
    navigation(
        route = RootScreen.Setting.route,
        startDestination = LeafScreen.Setting.route
    ) {
        showSetting(navigationActions)
    }
}

private fun NavGraphBuilder.showSetting(
    navigationActions: MainNavActions
) {
    composable(route = LeafScreen.Setting.route) {
        ProfileScreen(navigationActions)
    }
}

private fun NavGraphBuilder.addFavoriteRoute(
    navController: NavController,
    navigationActions: MainNavActions,
    openDrawer: () -> Unit
) {
    navigation(
        route = RootScreen.Favorites.route,
        startDestination = LeafScreen.Favorites.route
    ) {
        showFavorite(navController, navigationActions, openDrawer)
    }
}

private fun NavGraphBuilder.showFavorite(
    navController: NavController,
    navigationActions: MainNavActions,
    openDrawer: () -> Unit
) {
    composable(route = LeafScreen.Favorites.route) {
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