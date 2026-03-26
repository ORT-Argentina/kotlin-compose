package com.example.myapplication.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen() {
    Text(text = "Register Screen")
}

@Preview(showBackground = true)
@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun previewRegisterScreen(){
    RegisterScreen()
}
