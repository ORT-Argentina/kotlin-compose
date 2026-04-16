package com.eunoia.challenge1.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eunoia.challenge1.R

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(160.dp)      // Figma: 160px
            .height(60.dp),     // Figma: 60px
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.blueText),
            contentColor   = colorResource(R.color.white)
        ),
        shape = RoundedCornerShape(10.dp), // Figma: 10px
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
    ) {
        Text(text, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
    }
}
