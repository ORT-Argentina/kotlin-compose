package ar.edu.ort.bootsshop.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.MainActivityViewModel
import ar.edu.ort.bootsshop.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState,
    viewModel: MainActivityViewModel
) {
    TopBarInternal(
        title = { title.let { Text(it) } },
        onNavIconPressed = { viewModel.openDrawer() },
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
                modifier = Modifier.padding(10.dp).size(36.dp).clickable {
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

@Preview("Preview TopBar", showBackground = true)
@Preview("Preview TopBar (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    val drawerState = rememberDrawerState(initialValue = Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val title: String = "Preview TopBar"
    val viewModel: MainActivityViewModel = viewModel(factory = MainActivityViewModel.Factory)
    TopBar(title, scope, drawerState, snackbarHostState, viewModel)
}