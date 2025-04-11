package ar.edu.ort.challenge.holamundo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ar.edu.ort.challenge.holamundo.screens.welcome.WelcomeScreen
import ar.edu.ort.challenge.holamundo.ui.theme.HolaMundoTheme

@Composable
fun AppHolaMundo(){
    HolaMundoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            WelcomeScreen(
                modifier = Modifier.padding(innerPadding)
            )
            /*RegisterScreen(innerPadding)
            LoginScreen(innerPadding)*/
        }
    }
}

