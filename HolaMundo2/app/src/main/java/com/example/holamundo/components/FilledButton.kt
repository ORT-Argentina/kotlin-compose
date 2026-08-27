package com.example.holamundo.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilledButtonExample(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = { onClick() }) {
        Text("Filled")
    }
}