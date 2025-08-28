package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R

@Composable
fun WelcomeScreen(modifier: Modifier) {
    Column (modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "welcome"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun Prueba() {
    Text(text = "Hola" )
}