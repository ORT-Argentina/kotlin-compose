package ar.edu.ort.bootsshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.DrawerValue.Open
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.MainNavActions
import ar.edu.ort.bootsshop.R
import ar.edu.ort.bootsshop.data.ItemMenuShop

@Composable
fun AppDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = Closed),
    navigationActions: MainNavActions,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MenuDrawerContent(
                    navigationActions
                )
            }
        },
        //Para desactivar el Gesto (Hablamos en Clase)
        //gesturesEnabled = false,
        content = content
    )
}

@Composable
fun MenuDrawerContent(
    navigationActions: MainNavActions
) {

    //val context = LocalContext.current

    val itemMenu = listOf(
        ItemMenuShop(stringResource(R.string.list_title_bar), R.drawable.shop_list_icon , navigationActions.navigateToList),
        ItemMenuShop(stringResource(R.string.favorite_title_bar), R.drawable.favourites_icon, navigationActions.navigateToFavorite),
        ItemMenuShop("Profile", R.drawable.profile_icon, navigationActions.navigateToProfile),
        ItemMenuShop("Settings", R.drawable.setting_icon, navigationActions.navigateToSettings),
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        DrawerHeader()
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.drawer_menu_section_header)
        )
        itemMenu.forEach { item ->
            //Carga Dinamica de Imagenes
            /*val uri = "@drawable/${item.image}"
            val imageResource: Int = context.resources.getIdentifier(uri, null, context.packageName)*/
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = false,
                onClick = item.action,
                icon = {
                    Image(
                        modifier = Modifier.padding(6.dp),
                        painter = painterResource(item.image),
                        contentDescription = stringResource(R.string.drawer_menu_shop_list_image)
                    )
                }
            )
        }
    }
}

@Composable
private fun DrawerHeader() {
    Column (modifier = Modifier.padding(16.dp)) {
            Text(stringResource(R.string.drawer_menu_title))
    }
}

@Composable
@Preview("Drawer Menu Opened")
private fun Preview(){
    val drawerState = rememberDrawerState(initialValue = Open)
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val navigationActions = remember(navController) {
        MainNavActions(navController, scope, drawerState)
    }

    AppDrawer(
        drawerState = rememberDrawerState(initialValue = Open),
        navigationActions = navigationActions,
        content = {}
    )
}