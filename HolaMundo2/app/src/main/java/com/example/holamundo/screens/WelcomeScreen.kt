package com.example.holamundo.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.holamundo.components.FilledButtonExample
import com.example.holamundo.ui.theme.HolaMundoTheme

@Composable
fun WelcomeScreen( modifier: Modifier = Modifier){
    FilledButtonExample({}, modifier =  modifier  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeScreen(Modifier)
}