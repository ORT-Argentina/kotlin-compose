package ar.edu.ort.challenge.holamundo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import ar.edu.ort.challenge.holamundo.screens.welcome.WelcomeScreen
import ar.edu.ort.challenge.holamundo.screens.welcome.WelcomeViewModel
import ar.edu.ort.challenge.holamundo.ui.componets.AppHolaMundoBottomBar
import ar.edu.ort.challenge.holamundo.ui.componets.AppHolaMundoTopBar
import ar.edu.ort.challenge.holamundo.ui.theme.HolaMundoTheme
import kotlinx.coroutines.launch

@Composable
fun AppHolaMundo(viewModel: WelcomeViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    HolaMundoTheme{
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { AppHolaMundoTopBar() },
            bottomBar ={ AppHolaMundoBottomBar() },
            floatingActionButtonPosition = FabPosition.Center,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar", "Action",  withDismissAction = true, SnackbarDuration.Indefinite )
                        }
                    },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.Build, "Localized description")
                }
            }
        ) { innerPadding ->
            WelcomeScreen(
                modifier = Modifier.padding(innerPadding)
            )
            /*RegisterScreen(innerPadding)
            LoginScreen(innerPadding)*/
        }
    }
}

