package ar.edu.ort.challenge.first.screens.login

import android.renderscript.ScriptGroup.Input
import ar.edu.ort.challenge.first.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DisplayMode.Companion.Input
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(modifier: Modifier){
    Row(){
        Text("Login here")
        Text("Welcome back you’ve\n" +
                "been missed!")

    }
    /*Column(
        modifier = Modifier.fillMaxSize(),
        //verticalArrangement = Alignment.CenterVertically as Arrangement.Vertical,
    ){
        Box(modifier = Modifier.fillMaxWidth()){
            Column {
                Text(
                    text = stringResource(R.string.welcome_message),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }*/
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(Modifier)
}