package ar.edu.ort.challenge.holamundo.ui.componets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleComponent( modifier: Modifier = Modifier, name: String = "Android") {
    Text(
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        text = "Hello $name!",
        modifier = modifier
    )
}