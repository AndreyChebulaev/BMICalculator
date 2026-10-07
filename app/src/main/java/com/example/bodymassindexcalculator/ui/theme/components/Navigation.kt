package com.example.bodymassindexcalculator.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bodymassindexcalculator.Screen
import com.example.bodymassindexcalculator.ui.theme.BMITypography

@Composable
fun Navigation(screen: Screen, onChangeScreen: (Screen) -> Unit) {

    var calculatorStyle = BMITypography.PassiveNav
    var profileStyle = BMITypography.PassiveNav

    if (screen == Screen.Calculator) {
        calculatorStyle = BMITypography.ActiveNav
    } else {
        profileStyle = BMITypography.ActiveNav
    }

    Row(
        modifier = Modifier
            .shadow(elevation = 20.dp)
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .clickable{onChangeScreen(Screen.Calculator)},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Калькулятор",
                style = calculatorStyle
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clickable{onChangeScreen(Screen.Profile)},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Профиль",
                style = profileStyle
            )
        }
    }
}