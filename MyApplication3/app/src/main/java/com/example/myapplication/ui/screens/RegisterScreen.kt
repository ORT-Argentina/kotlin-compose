package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.Message
//import coil3.compose.AsyncImage
import com.example.myapplication.ui.components.ChatComponent

@Composable
fun RegisterScreen(navController1: Modifier, navController: NavHostController) {

    var lista = listOf(
        Message("Martin", "Hola", "10:00", avatar = ""), Message("Ana", "Hola", "11:00", avatar = "")
    )

    LazyColumn {
        item(){
            ChatComponent("Nombre de Grupo", "Item Fijo", "Hora")
        }
        items(lista) { mensaje ->
            ChatComponent(mensaje.nombre, mensaje.ultMensaje, mensaje.hora)
        }
        item(){
            ChatComponent("Nombre del contacto", "Item Fijo 2", "Hora")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(Modifier.fillMaxSize(), navController)
}