package ar.edu.ort.bootsshop.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.ort.bootsshop.AppIcon
import ar.edu.ort.bootsshop.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState
) {
    TopBarInternal(
        title = { title.let { Text(it) } },
        onNavIconPressed = { scope.launch {
            drawerState.open()
        } },
        scope = scope,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarInternal(
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