package ar.edu.ort.challenge.first

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ar.edu.ort.challenge.first.screens.welcome.WelcomeScreen
import ar.edu.ort.challenge.first.ui.theme.MyApplicationTheme

@Composable
fun AppFirstChallange(){
    MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                WelcomeScreen(Modifier.padding(innerPadding))
                //LoginScreen(Modifier)
                //RegisterScreen(Modifier)
        }
    }
}