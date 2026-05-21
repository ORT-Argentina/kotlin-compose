package ar.edu.ort.frases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ar.edu.ort.frases.navigation.FrasesNavHost
import ar.edu.ort.frases.ui.theme.FrasesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FrasesTheme {
                FrasesNavHost()
            }
        }
    }
}