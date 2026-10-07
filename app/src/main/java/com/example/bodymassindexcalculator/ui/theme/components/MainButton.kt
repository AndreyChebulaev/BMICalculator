package com.example.bodymassindexcalculator.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bodymassindexcalculator.ui.theme.BMIGreen
import com.example.bodymassindexcalculator.ui.theme.BMITypography

@Composable
fun MainButton(text: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable{onClick()}
            .background(color = BMIGreen, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(0.8f)
            .padding(12.dp)
    ) {
        Text(
            text = text,
            style = BMITypography.Button
        )
    }
}