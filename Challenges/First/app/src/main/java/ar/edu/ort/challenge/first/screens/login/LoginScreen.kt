package ar.edu.ort.challenge.first.screens.login

import ar.edu.ort.challenge.first.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(modifier: Modifier){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Alignment.CenterVertically as Arrangement.Vertical,
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
    }
}