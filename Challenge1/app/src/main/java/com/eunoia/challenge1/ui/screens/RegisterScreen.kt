package com.eunoia.challenge1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eunoia.challenge1.R
import com.eunoia.challenge1.ui.components.AuthTextField
import com.eunoia.challenge1.ui.components.PrimaryButton
import com.eunoia.challenge1.ui.components.SocialIcon

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onSignUp: () -> Unit = {},
    onGoLogin: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.3f))

        Text(
            text = stringResource(R.string.register_title),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = colorResource(R.color.blueText)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.register_subtitle),
            fontSize = 14.sp,
            lineHeight = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            placeholderRes = R.string.login_email_placeholder,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        AuthTextField(
            value = pass,
            onValueChange = { pass = it },
            placeholderRes = R.string.login_password_placeholder,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        AuthTextField(
            value = pass2,
            onValueChange = { pass2 = it },
            placeholderRes = R.string.register_confirm_password,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        PrimaryButton(
            text = stringResource(R.string.register_sign_up_button),
            modifier = Modifier.fillMaxWidth(),
            onClick = onSignUp
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.register_have_account),
            color = colorResource(R.color.greySubt),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.login_continue_with),
            color = colorResource(R.color.blueText),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialIcon(R.drawable.ic_google)
            SocialIcon(R.drawable.ic_facebook)
            SocialIcon(R.drawable.ic_apple)
        }

        Spacer(Modifier.weight(0.5f))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}