package ar.edu.ort.challenge.holamundo.screens.register

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreen(innerPadding: PaddingValues) {
    Text(text = "Register", modifier = Modifier.padding(innerPadding))
}