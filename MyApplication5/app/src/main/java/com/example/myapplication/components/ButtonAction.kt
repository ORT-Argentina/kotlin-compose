package com.example.myapplication.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonAction(texto: String) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = texto)
    }
}

@Preview
@Composable
fun ButtonActionPreview(){
    ButtonAction("Texto")
}