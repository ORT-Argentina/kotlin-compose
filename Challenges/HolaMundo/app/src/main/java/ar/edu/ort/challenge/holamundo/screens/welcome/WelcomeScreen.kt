package ar.edu.ort.challenge.holamundo.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.ort.challenge.holamundo.R
import ar.edu.ort.challenge.holamundo.ui.theme.Black


@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ){
        Row(modifier = Modifier.fillMaxWidth().weight(0.5F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth().weight(0.5F)) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.welcome_image_1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.width(343.dp),
                        text = stringResource(R.string.welcome_title),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.width(323.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.welcome_subtitle)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                shape = RoundedCornerShape(10),
                onClick = {  },
            ) {
                Text(stringResource(id = R.string.welcome_btn_action_login))
            }
            Spacer(modifier = Modifier.size(70.dp))
            TextButton(
                onClick = { /* Do something! */ },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Black
                ),
            ) {
                Text(stringResource(id = R.string.welcome_btn_action_register))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeScreen(Modifier.padding(10.dp))
}