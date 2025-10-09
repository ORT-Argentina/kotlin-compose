package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun ChatComponent(titulo: String, mensaje: String, hora: String) {
    Row() {
        /*AsyncImage(
            model = "https://cdn2.iconfinder.com/data/icons/avatars-60/5985/24-Maid-1024.png",
            contentDescription = null,
        )*/
        Image(
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "avatar",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Row() {
            Column() {
                Text(text = titulo)
                Text(text = mensaje)
            }
            Column() {
                Text(text = hora)
                Text(text = "hora")
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}