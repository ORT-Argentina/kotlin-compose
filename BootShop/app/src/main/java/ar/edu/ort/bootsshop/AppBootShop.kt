package ar.edu.ort.bootsshop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.data.Sizes
import ar.edu.ort.bootsshop.ui.components.TopBar
import ar.edu.ort.bootsshop.ui.components.AppDrawer
import ar.edu.ort.bootsshop.ui.components.AppSnackBar
import ar.edu.ort.bootsshop.ui.theme.BackgroundColor
import ar.edu.ort.bootsshop.ui.theme.BootShopTheme
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


        val navigationActions = remember(navController) {
            MainNavActions(navController, scope, drawerState, snackbarHostState)
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
                floatingActionButton = { FloatButton(onClick = { scope.launch {
                    snackbarHostState.showSnackbar("Floating Action")
                }}) },
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