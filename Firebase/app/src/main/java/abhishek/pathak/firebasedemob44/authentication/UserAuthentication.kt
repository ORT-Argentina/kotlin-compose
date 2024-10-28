package abhishek.pathak.firebasedemob44.authentication

import abhishek.pathak.firebasedemob44.R
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    val viewModel: UserAuthenticationViewModel = viewModel()
    val context = LocalContext.current
    var showPassword: Boolean by remember {
        mutableStateOf(false)
    }
    var isLoginScreen: Boolean by remember {
        mutableStateOf(false)
    }

    val loginStatus = viewModel.loginStatus.observeAsState()
    val registerStatus = viewModel.registerStatus.observeAsState()
    val verifyEmailStatus = viewModel.verifyEmailStatus.observeAsState()


    registerStatus.value?.let {
        showToast(context, it)
    }

    loginStatus.value?.let {
        showToast(context, it)
    }

    loginStatus.value?.let {
        showToast(context, it)
    }

    Box(
        modifier = Modifier
            .background(color = Color.Blue)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.baseline_bloodtype_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = if (isLoginScreen) context.getString(R.string.login_label)
                else context.getString(R.string.register_label),
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            LabeledSwitch(
                label = Pair(
                    context.getString(R.string.login_label),
                    context.getString(R.string.register_label)
                ),
                textColor = Color.White,
                modifier = Modifier
                    .padding(24.dp)
            ) { isLoginScreen = it }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                shape = RoundedCornerShape(24.dp),
                label = { Text(text = "Email", color = Color.Blue) },
                placeholder = { Text(text = "", color = Color.Blue) },
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                shape = RoundedCornerShape(24.dp),
                label = { Text(text = "Password", color = Color.Blue) },
                placeholder = { Text(text = "", color = Color.Blue) },
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Outlined.VisibilityOff
                            else Icons.Outlined.Visibility,
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            )
            Text(
                text = "Forgot password?",
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(20.dp))

            OutlinedButton(
                border = BorderStroke(1.dp, Color.White),
                onClick = {
                    invokeViewModelMethods(
                        context = context,
                        isLoginScreen = isLoginScreen,
                        viewModel = viewModel
                    )
                }) {
                Text(
                    text = if (isLoginScreen) context.getString(R.string.login_label)
                    else context.getString(R.string.register_label)
                )
            }
        }
    }
}

@Composable
fun LabeledSwitch(
    label: Pair<String, String>, // first: off, second: on
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    switchState: MutableState<Boolean> = remember { mutableStateOf(false) },
    onChange: (Boolean) -> Unit
) {
    Row(modifier) {
        Switch(
            checked = switchState.value,
            onCheckedChange = { onChange(it); switchState.value = it }
        )
        Text(
            text = if (switchState.value) label.second else label.first,
            modifier = textModifier,
            color = textColor
        )
    }
}

fun showToast(context: Context, it: String) {
    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
}


private fun invokeViewModelMethods(
    context: Context,
    isLoginScreen: Boolean,
    viewModel: UserAuthenticationViewModel
) = with(viewModel) {
    if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
      /*  if (isLoginScreen) {
            login()
        } else {

        }*/

       // register()

        login()
    } else {
        showToast(context, context.getString(R.string.empty_login))
    }
}