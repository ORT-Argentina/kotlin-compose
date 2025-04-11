package ar.edu.ort.challenge.holamundo.screens.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(innerPadding: PaddingValues) {
    Text(text = "Login", modifier = Modifier.padding(innerPadding))
}