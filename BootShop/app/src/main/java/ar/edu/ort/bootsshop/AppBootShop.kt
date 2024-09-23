package ar.edu.ort.bootsshop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.data.Sizes
import ar.edu.ort.bootsshop.ui.components.AppDrawer
import ar.edu.ort.bootsshop.ui.theme.BackgroundColor
import ar.edu.ort.bootsshop.ui.theme.BootShopTheme
import ar.edu.ort.bootsshop.ui.theme.Brown40
import ar.edu.ort.bootsshop.ui.theme.SecondaryButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val sizes = enumValues<Sizes>()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBootShop(viewModel: MainActivityViewModel = MainActivityViewModel()) {
    BootShopTheme {

        val drawerState = rememberDrawerState(initialValue = Closed)
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
                snackbarHost = { SnackBarApp(snackbarHostState) },
                containerColor = BackgroundColor,
                topBar = {
                    AppBar(
                        title = { title.let { Text(it) } },
                        onNavIconPressed = { scope.launch {
                            drawerState.open()
                        } },
                        scope = scope,
                        snackbarHostState = snackbarHostState
                    )
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
fun SnackBarApp(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
             Snackbar(
                 containerColor = Brown40,
                 modifier = Modifier.padding(16.dp),
                 content = {
                     Text(
                         text = data.visuals.message,
                         style = MaterialTheme.typography.bodyLarge
                     )
                 }
             )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
){
    CenterAlignedTopAppBar(
        modifier = modifier,
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.trailing_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp).padding(start = 30.dp, end = 16.dp, top = 16.dp).clickable {
                    scope.launch {
                        snackbarHostState.showSnackbar("Avatar")
                    }
                }
            )
          },
        title = title,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            AppIcon(
                contentDescription = stringResource(id = R.string.navigation_drawer_open),
                modifier = Modifier
                    .size(64.dp)
                    .clickable(onClick = onNavIconPressed)
                    .padding(16.dp)
            )
        }
    )
}

@Composable
fun AppIcon(
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }
    Box(modifier = modifier.then(semantics)) {
        Icon(
            painter = painterResource(id = R.drawable.leading_icon),
            contentDescription = null
        )
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
    AppBootShop()
}