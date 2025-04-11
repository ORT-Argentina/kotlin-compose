package ar.edu.ort.bootsshop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.data.Sizes
import ar.edu.ort.bootsshop.navigation.RootScreen
import ar.edu.ort.bootsshop.ui.components.AppBottomBar
import ar.edu.ort.bootsshop.ui.components.TopBar
import ar.edu.ort.bootsshop.ui.components.AppDrawer
import ar.edu.ort.bootsshop.ui.components.AppSnackBar
import ar.edu.ort.bootsshop.ui.theme.BackgroundColor
import ar.edu.ort.bootsshop.ui.theme.BootShopTheme
import ar.edu.ort.bootsshop.ui.theme.Brown40
import ar.edu.ort.bootsshop.ui.theme.SecondaryButton
import kotlinx.coroutines.launch

val sizes = enumValues<Sizes>()

@Composable
fun AppBootShop(
    viewModel: MainActivityViewModel = viewModel(factory = MainActivityViewModel.Factory),
    drawerState: DrawerState
) {
    BootShopTheme {
        val navController = rememberNavController()

        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val title: String by viewModel.titleBar.observeAsState(stringResource(R.string.title_bar_default))

        val currentSelectedScreen by navController.currentScreenAsState(viewModel)
        //val currentRoute by navController.currentRouteAsState()
        
        val navigationActions = remember(navController) {
            MainNavActions(navController, scope, drawerState)
        }

        if (drawerState.isOpen) {
            BackHandler {
                scope.launch {
                    drawerState.close()
                }
            }
        }

        AppDrawer(
            drawerState = drawerState,
            navigationActions
        ) {
            Scaffold(
                floatingActionButton = { FloatingActionButton(
                    modifier = Modifier.absoluteOffset(x = 0.dp, y = 55.dp),
                    containerColor = Brown40,
                    contentColor = Color.White,
                    shape = CircleShape,
                    onClick = { scope.launch {
                    snackbarHostState.showSnackbar("Floating Action")
                }}){
                    Icon(
                        modifier = Modifier.shadow(7.dp),
                        painter = painterResource(id = R.drawable.icon_shop),
                        contentDescription = stringResource(id = R.string.bottom_title_shop)
                    )
                } },
                floatingActionButtonPosition = FabPosition.Center,
                //isFloatingActionButtonDocked = true,
                bottomBar = { AppBottomBar(navigationActions, viewModel)},
                snackbarHost = { AppSnackBar(snackbarHostState) },
                containerColor = BackgroundColor,
                topBar = {
                    TopBar(title, scope, drawerState, snackbarHostState, viewModel)
                 },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                MainRouteNavGraph(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    viewModel = viewModel,
                    openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    navigationActions = navigationActions
                )
            }
        }
    }

}

@Composable
fun FloatButton(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = SecondaryButton,
        onClick = { onClick() },
    ) {
        Icon(painter = painterResource(id = R.drawable.float_message),
            contentDescription = null)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val drawerState = rememberDrawerState(initialValue = Closed)
    AppBootShop(drawerState = drawerState)
}

@Stable
@Composable
private fun NavController.currentScreenAsState(viewModel: MainActivityViewModel): State<RootScreen> {
    val selectedItem = remember { mutableStateOf<RootScreen>(RootScreen.Home) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == RootScreen.Home.route } -> {
                    viewModel.setTitleBar("Shop List")
                    viewModel.setRoute(RootScreen.Home)
                }
                destination.hierarchy.any { it.route == RootScreen.Search.route } -> {
                    viewModel.setTitleBar("Search")
                    viewModel.setRoute(RootScreen.Search)
                }
                destination.hierarchy.any { it.route == RootScreen.Favorites.route } -> {
                    viewModel.setTitleBar("Favourite")
                    viewModel.setRoute(RootScreen.Favorites)
                }
                destination.hierarchy.any { it.route == RootScreen.Profile.route } -> {
                    viewModel.setTitleBar("Profile")
                    viewModel.setRoute(RootScreen.Profile)
                }
                destination.hierarchy.any { it.route == RootScreen.Setting.route } -> {
                    viewModel.setTitleBar("Setting")
                    viewModel.setRoute(RootScreen.Setting)
                }
            }
        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

/*@Stable
@Composable
private fun NavController.currentRouteAsState(): State<String?> {
    val selectedItem = remember { mutableStateOf<String?>(null) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = destination.route
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}*/
