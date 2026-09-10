package com.example.myapplication.ui.components

import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SwitchMinimalExample() {
    var checked by remember { mutableStateOf(true) }
    Text("Hola!")
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}