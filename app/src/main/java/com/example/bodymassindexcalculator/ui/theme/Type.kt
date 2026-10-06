package com.example.bodymassindexcalculator.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
object BMITypography {
    val Title = TextStyle(
        color = BMIGreen,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    val Input = TextStyle(
        color = BMIGray,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textAlign = TextAlign.Start
    )

    val Button = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )

    val Error = TextStyle(
        color = BMIRed,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )

    val Complementary = TextStyle(
        color = BMIGray,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}