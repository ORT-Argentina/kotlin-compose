package com.eunoia.challenge1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eunoia.challenge1.R

// 1) Definír la familia
val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),   // 400
    Font(R.font.poppins_semibold, FontWeight.SemiBold) // 600

)

// 2) Tipografía global de la app usando Poppins
val AppTypography = Typography(

    bodyMedium = TextStyle(            // para párrafos
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(            // para títulos grandes
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 35.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(            // para botones (“Login”)
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )
)
