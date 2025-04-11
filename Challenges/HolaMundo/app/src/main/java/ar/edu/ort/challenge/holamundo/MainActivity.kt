package ar.edu.ort.challenge.holamundo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppHolaMundo()
        }
    }

    override fun onStop() {
        super.onStop()
        // TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()

    }
}
