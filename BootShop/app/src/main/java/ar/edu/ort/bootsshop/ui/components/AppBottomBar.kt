package ar.edu.ort.bootsshop.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.provider.DocumentsContract.Root
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.AppDestinations
import ar.edu.ort.bootsshop.MainActivityViewModel
import ar.edu.ort.bootsshop.MainNavActions
import ar.edu.ort.bootsshop.R
import ar.edu.ort.bootsshop.navigation.RootScreen
import ar.edu.ort.bootsshop.ui.theme.BackgroundColor

@Composable
fun AppBottomBar(
    navActions: MainNavActions,
    viewModel: MainActivityViewModel
) {
    val space = 20.dp
    BottomAppBar (
        modifier = Modifier.padding(0.dp),
        containerColor = Color.White,
    ) {
        NavigationBarItem(
            modifier = Modifier.weight(1f),
            icon = {Icon(
                painter = painterResource(R.drawable.icon_home),
                contentDescription = stringResource(id = R.string.bottom_title_product)
            )},
            label = {
                Text( text = stringResource(id = R.string.bottom_title_product), fontSize = 12.sp)
            },
            alwaysShowLabel = true,
            onClick = navActions.navigateToList,
            selected = viewModel.getRoute() == RootScreen.Home,
        )
        NavigationBarItem(
            modifier = Modifier.weight(1f),
            icon = {Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = stringResource(id = R.string.bottom_title_search)
            )},
            label = {
                Text( text = stringResource(id = R.string.bottom_title_search), fontSize = 12.sp)
            },
            alwaysShowLabel = true,
            onClick = navActions.navigateToFavorite,
            selected = viewModel.getRoute() == RootScreen.Favorites,
        )
        Spacer(modifier = Modifier.size(space))
        Box(
            modifier = Modifier
                .drawWithCache {
                    val roundedPolygon = RoundedPolygon(
                        numVertices = 8,
                        radius = size.minDimension / 1.4f,
                        centerX = size.width / 2f,
                        centerY = 0f,
                        rounding = CornerRounding(
                            size.minDimension / 1f,
                            smoothing = 0.1f
                        )
                    )
                    val roundedPolygonPath = roundedPolygon.toPath().asComposePath()
                    onDrawBehind {
                        drawPath(roundedPolygonPath, color = BackgroundColor)
                    }
                }
                .size(75.dp)
        ) {
            /*FloatingActionButton(
                onClick = {  },
                containerColor = Brown40,
                contentColor = Color.White,
            ) {
                Icon(painter = painterResource(R.drawable.icon_shop),
                    contentDescription = stringResource(id = R.string.bottom_title_shop))
            }*/
        }
        Spacer(modifier = Modifier.size(space))
        NavigationBarItem(
            modifier = Modifier.weight(1f),
            icon = {Icon(
                painter = painterResource(R.drawable.icon_cart),
                contentDescription = stringResource(id = R.string.bottom_title_cart)
            )},
            label = {
                Text( text = stringResource(id = R.string.bottom_title_cart), fontSize = 12.sp)
            },
            alwaysShowLabel = true,
            onClick = navActions.navigateToSettings,
            selected = viewModel.getRoute() == RootScreen.Setting,
        )
        NavigationBarItem(
            modifier = Modifier.weight(1f),
            icon = {Icon(
                painter = painterResource(R.drawable.icon_user),
                contentDescription = stringResource(id = R.string.bottom_title_user)
            )},
            label = {
                Text( text = stringResource(id = R.string.bottom_title_user), fontSize = 12.sp)
            },
            alwaysShowLabel = true,
            onClick = navActions.navigateToProfile,
            selected = viewModel.getRoute() == RootScreen.Profile,
        )
    }
}


@Preview("Preview BotttomBar", showBackground = true)
@Preview("Preview BotttomBar (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AppBottomBarPreview() {
    val viewModel: MainActivityViewModel = viewModel(factory = MainActivityViewModel.Factory)
    val drawerState = rememberDrawerState(initialValue = Closed)
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navigationActions = remember(navController) {
        MainNavActions(navController, scope, drawerState)
    }
    AppBottomBar(navigationActions, viewModel)
}