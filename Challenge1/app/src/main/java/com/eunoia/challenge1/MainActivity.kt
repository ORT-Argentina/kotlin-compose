package com.eunoia.challenge1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eunoia.challenge1.ui.navigation.Screen
import com.eunoia.challenge1.ui.screens.LoginScreen
import com.eunoia.challenge1.ui.screens.RegisterScreen
import com.eunoia.challenge1.ui.screens.WelcomeScreen
import com.eunoia.challenge1.ui.theme.Challenge1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Challenge1Theme {
                val nav = rememberNavController()


                Scaffold(Modifier.fillMaxSize()) { inner ->
                    NavHost(
                        navController = nav,
                        startDestination = Screen.Welcome.route,
                        modifier = Modifier.padding(inner)
                    ) {
                        composable(Screen.Welcome.route) {
                            WelcomeScreen(
                                onLogin    = { nav.navigate(Screen.Login.route) },
                                onRegister = { nav.navigate(Screen.Register.route) }
                            )
                        }
                        composable(Screen.Login.route) {
                            LoginScreen(
                                onForgot     = { /* TODO */ },
                                onSignIn     = {
                                    nav.navigate(Screen.Welcome.route) {
                                        popUpTo(Screen.Welcome.route) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                },
                                onGoRegister = { nav.navigate(Screen.Register.route) }
                            )
                        }
                        composable(Screen.Register.route) {
                            RegisterScreen(
                                onSignUp = { /* TODO */ },
                                onGoLogin = { nav.navigate(Screen.Login.route) }
                            )
                        }

                    }
                }
            }
        }
    }
}
