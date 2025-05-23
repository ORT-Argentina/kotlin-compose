package com.example.simulacro2025

import CoffeeWelcomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simulacro2025.container.Container
import com.example.simulacro2025.imageonboarding.ImageOnboarding
import com.example.simulacro2025.screen.HomeScreen
import com.example.simulacro2025.ui.theme.PurpleGrey40
import com.example.simulacro2025.ui.theme.Simulacro2025Theme

@Composable
fun AppSimulacro2025() {
    Simulacro2025Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Splash") {
                composable("Splash") {
                    CoffeeWelcomeScreen(
                        Modifier.padding(innerPadding),
                        onGetStartedClick = {
                            navController.navigate("Home")
                        }
                    )
                }
                composable("Home") {
                    HomeScreen()
                }
                composable("Relay"){
                    ImageOnboarding()
                }
                composable("Test"){
                    Container(Modifier, PurpleGrey40, {})
                }
            }
        }
    }
}