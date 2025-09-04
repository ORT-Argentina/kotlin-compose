package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R

@Composable
fun WelcomeScreen(modifier: Modifier, navController: NavHostController) {
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "welcome"
        )
        Text(text = stringResource(R.string.welcomescreen_btn_submit), style = MaterialTheme.typography.bodyLarge  )
        Button(onClick = { navController.navigate("register") }) {
            Text(text = "Register")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(Modifier.fillMaxSize(), navController)
}

@Preview(showBackground = true)
@Composable
fun Prueba() {

}