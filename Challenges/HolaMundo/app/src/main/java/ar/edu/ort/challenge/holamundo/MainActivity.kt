package ar.edu.ort.challenge.holamundo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import ar.edu.ort.challenge.holamundo.screens.welcome.WelcomeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContent {
            AppHolaMundo(viewModel)
        }
    }

    override fun onStart() {
        super.onStart()

        // TODO("Not yet implemented")
    }

    override fun onStop() {
        super.onStop()
        // TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()

    }
}