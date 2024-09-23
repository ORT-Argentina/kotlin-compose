package ar.edu.ort.bootsshop.screens.favourite

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.MainNavActions
import ar.edu.ort.bootsshop.R
import ar.edu.ort.bootsshop.data.Boot
import ar.edu.ort.bootsshop.data.FavouriteItems
import ar.edu.ort.bootsshop.ui.theme.Brown40
import ar.edu.ort.bootsshop.ui.theme.Purple40
import ar.edu.ort.bootsshop.ui.theme.WhiteApp

@Composable
fun FavouriteScreen(
    navController: NavHostController,
    favouriteViewModel: FavouriteViewModel,
    navigationActions: MainNavActions,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val state = rememberLazyListState()

    LazyColumn (Modifier.fillMaxSize(), state) {
        items(FavouriteItems) { boot ->
            BootItem(
                boot,
                modifier = Modifier,
                navigationActions
            )
        }
    }
}

@Composable
fun BootItem(boot: Boot, modifier: Modifier = Modifier, navigationActions: MainNavActions) {

    Card(
        shape = RoundedCornerShape(12.dp),
        border = null,
        colors = CardDefaults.cardColors(
            containerColor = if( !isSystemInDarkTheme() )  WhiteApp else Purple40,
        ),
        modifier = modifier.fillMaxWidth()
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                navigationActions.navigateToDetail(boot.id)
            }
    ){
        Row( modifier = Modifier.fillMaxWidth() ) {
            Text(
                modifier = Modifier
                    .padding(30.dp)
                    .drawBehind {
                        drawCircle(
                            color = Brown40,
                            radius = this.size.maxDimension
                        )
                    },
                fontWeight = FontWeight.Bold,
                color = WhiteApp,
                text = boot.id.toString(),
            )
            Column(modifier = Modifier.padding(start = 16.dp, bottom = 10.dp).weight(1f)) {
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    text = boot.title,
                )
                Text(
                    fontSize = 14.sp,
                    text = String.format("$ %s", boot.price.toString()),
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
            Image(
                painter = painterResource(id = R.drawable.boot),
                contentDescription = "Android Logo",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview("Favorites Screen", showBackground = true)
@Preview("Favorites (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val navigationActions = remember(navController) {
        MainNavActions(navController, scope, drawerState, snackbarHostState)
    }
    val viewModel: FavouriteViewModel = viewModel(
        factory = FavouriteViewModel.provideFactory()
    )
    FavouriteScreen(navController, viewModel, navigationActions, {}, snackbarHostState)
}