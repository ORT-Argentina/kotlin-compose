package ar.edu.ort.challenge.holamundo.screens.welcome
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    public var isLogin: Boolean = false

    var checked = mutableStateOf(true)

}