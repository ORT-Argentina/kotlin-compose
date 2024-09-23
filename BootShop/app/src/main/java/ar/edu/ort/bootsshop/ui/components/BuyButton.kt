package ar.edu.ort.bootsshop.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ar.edu.ort.bootsshop.R
import ar.edu.ort.bootsshop.ui.theme.Brown40
import ar.edu.ort.bootsshop.ui.theme.Pink80

@Composable
fun BuyButton() {
    val buttonColor = if( !isSystemInDarkTheme() )  Brown40 else Pink80

    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = buttonColor
        ),
        onClick = { /* Do something! */ },
    ) {
        Text(stringResource(id = R.string.list_screen_btn_buy))
    }
}