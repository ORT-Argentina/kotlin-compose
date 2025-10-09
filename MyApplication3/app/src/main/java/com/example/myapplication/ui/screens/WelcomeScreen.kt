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
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.myapplication.DashboardActivity
import com.example.myapplication.ui.components.SwitchMinimalExample

@Composable
fun WelcomeScreen(modifier: Modifier, navController: NavHostController, intent: Intent) {
    val context = LocalContext.current
    Column (modifier = Modifier.fillMaxSize()) {
        var name = remember { mutableStateOf("Martin") }

        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "welcome"
        )

        Text(text = stringResource(R.string.welcomescreen_btn_submit), style = MaterialTheme.typography.bodyLarge  )
        Button(onClick = {

            navController.navigate("register")
            //val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse("tel:111") )
            //intentCall.data = Uri.parse("tel:123456789")
            /*val intent = Intent(context, DashboardActivity::class.java)

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }*/
            //context.startActivity(intent)

        }) {
            Text(text = "Register")

        }
        val nombre = "Martin"

        Text(
            text = "Hola, ${name.value}"
        )
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Nombre") }
        )
        SwitchMinimalExample()
    }
}




@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    val intent = null
    WelcomeScreen(Modifier.fillMaxSize(), navController, Intent(""))
}

@Preview(showBackground = true)
@Composable
fun Prueba() {

}