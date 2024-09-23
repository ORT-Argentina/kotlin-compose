package ar.edu.ort.bootsshop.screens.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.MainNavActions
import ar.edu.ort.bootsshop.R
import ar.edu.ort.bootsshop.data.Boot
import ar.edu.ort.bootsshop.data.BootListItems
import ar.edu.ort.bootsshop.ui.components.BuyButton
import ar.edu.ort.bootsshop.ui.theme.Brown40
import ar.edu.ort.bootsshop.ui.theme.Pink80
import ar.edu.ort.bootsshop.ui.theme.Purple40
import ar.edu.ort.bootsshop.ui.theme.WhiteApp

@Composable
fun ListScreen(bootList: List<Boot>, navigationActions: MainNavActions, innerPadding: PaddingValues) {

    val state = rememberLazyListState()

    LazyColumn (Modifier.fillMaxSize(), state) {
        items(bootList) { boot ->
            BootItem(
                boot,
                modifier = Modifier.padding(innerPadding),
                navigationActions
            )
        }
    }
}

@Composable
fun BootItem(boot: Boot, modifier: Modifier = Modifier, navigationActions: MainNavActions) {

    val buttonColor = if( !isSystemInDarkTheme() )  Brown40 else Pink80

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
        Image(
            painter = painterResource(id = R.drawable.boot),
            contentDescription = "Android Logo",
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Crop
        )
        Column( modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)) {
            Spacer(modifier = Modifier.size(16.dp).fillMaxWidth())
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
            Text(
                fontSize = 14.sp,
                text = boot.content,
            )
            Row (
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    border = BorderStroke(
                        width = 1.dp,
                        color = buttonColor
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = buttonColor
                    ),
                    onClick = { /* Do something! */ },
                ) {
                    Text(stringResource(id = R.string.list_screen_btn_add_favourite))
                }
                Spacer(modifier = Modifier.size(16.dp))
                BuyButton()
            }
        }
    }
}

@Preview("List Screen", showBackground = true)
@Preview("List Screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val navigationActions = remember(navController) {
        MainNavActions(navController, scope, drawerState, snackbarHostState)
    }

    ListScreen(BootListItems, navigationActions, PaddingValues(16.dp))
}