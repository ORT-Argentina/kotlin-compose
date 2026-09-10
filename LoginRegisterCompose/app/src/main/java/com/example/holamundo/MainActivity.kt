package com.example.holamundo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.holamundo.components.FilledButtonExample
import com.example.holamundo.screens.LoginScreen
import com.example.holamundo.screens.RegisterScreen
import com.example.holamundo.screens.WelcomeScreen
import com.example.holamundo.ui.theme.HolaMundoTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HolaMundoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   val modifier = Modifier.padding(innerPadding)
                    WelcomeScreen(modifier)
                    RegisterScreen(modifier)
                    LoginScreen(modifier)
                }
            }
        }
    }
}