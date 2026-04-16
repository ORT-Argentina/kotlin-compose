package com.eunoia.challenge1.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eunoia.challenge1.R

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderRes: Int,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(stringResource(placeholderRes), color = colorResource(R.color.greySubt)) },
        modifier = modifier,
        singleLine = singleLine,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor   = colorResource(R.color.fondo),
            unfocusedContainerColor = colorResource(R.color.fondo)
        )
    )
}
