package com.example.myapplication.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Greeting
import com.example.myapplication.R
import com.example.myapplication.components.ButtonAction
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.poppinsFonts

@Composable
fun WelcomeScreen(){
    Column {
        Image(
            painter = painterResource(id = R.drawable.welcome_image_2),
            contentDescription = "Welcome Image",
        )
        Text(
            text = "Discover Your Dream Job here",
            fontFamily = poppinsFonts,
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Explore all the existing job roles based on your interest and study major"
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.padding(horizontal = 50.dp)) {
            ButtonAction()
            ButtonAction()
        }

    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        WelcomeScreen()
    }
}