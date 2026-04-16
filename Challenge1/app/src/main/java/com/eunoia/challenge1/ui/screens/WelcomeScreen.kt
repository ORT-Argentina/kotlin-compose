package com.eunoia.challenge1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eunoia.challenge1.R
import com.eunoia.challenge1.ui.components.PrimaryButton

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit = {},
    onRegister: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.welcome_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(422.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.welcome_title),
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 52.sp,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.blueText),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.welcome_subtitle),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = colorResource(R.color.greySubt),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(
                modifier = Modifier.width(160.dp),
                text = stringResource(R.string.welcome_login_button),
                onClick = onLogin
            )
            Spacer(modifier = Modifier.width(1.dp))
            TextButton(onClick = onRegister, modifier = Modifier.width(160.dp)) {
                Text(
                    text = stringResource(R.string.welcome_register_button),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = colorResource(R.color.black)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}