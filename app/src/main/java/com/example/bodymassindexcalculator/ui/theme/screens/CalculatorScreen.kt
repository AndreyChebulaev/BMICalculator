package com.example.bodymassindexcalculator.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bodymassindexcalculator.Screen
import com.example.bodymassindexcalculator.ui.theme.components.MainCard
import com.example.bodymassindexcalculator.ui.theme.BMIGrayLight
import com.example.bodymassindexcalculator.ui.theme.BMIGreenLight
import com.example.bodymassindexcalculator.ui.theme.BMITypography
import com.example.bodymassindexcalculator.ui.theme.CalculatorViewModel
import com.example.bodymassindexcalculator.ui.theme.components.MainButton
import com.example.bodymassindexcalculator.ui.theme.components.Navigation
import com.example.bodymassindexcalculator.ui.theme.components.TextInput

@Composable
fun CalculatorScreen(changeScreen: (Screen) -> Unit) {

    val viewModel: CalculatorViewModel = viewModel()
    val result by viewModel.result.collectAsState()
    val errorText by viewModel.errorText.collectAsState()

    val heightState = rememberTextFieldState()
    val weightState = rememberTextFieldState()

    val inputTransformation = InputTransformation {
        if (!asCharSequence().isDigitsOnly()) {
            revertAllChanges()
        }
    }
    Scaffold(
        bottomBar = { Navigation(Screen.Calculator, changeScreen) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BMIGrayLight)
                .padding(innerPadding)
                .offset(y = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Индекс массы тела", style = BMITypography.Title.copy(fontSize = 24.sp))
            MainCard(Modifier.fillMaxHeight(0.35f).padding(vertical = 24.dp)) {
                Text(
                    text = "Персональные данные",
                    style = BMITypography.Title.copy(fontSize = 16.sp),
                    modifier = Modifier.padding(top = 8.dp)
                )
                TextInput(
                    label = "Рост (см)",
                    placeholder = "185",
                    textState = heightState,
                    inputTransformation = inputTransformation
                )

                TextInput(
                    label = "Вес (кг)",
                    placeholder = "77",
                    textState = weightState,
                    modifier = Modifier.padding(bottom = 8.dp),
                    inputTransformation = inputTransformation
                )
            }
            MainButton("РАССЧИТАТЬ") {
                viewModel.calculateResult(heightState.text.toString(), weightState.text.toString())
            }
            errorText?.let {
                Text(
                    modifier = Modifier.offset(y = 12.dp),
                    text = it,
                    style = BMITypography.Error
                )
            }
            result?.let {
                MainCard(
                    color = BMIGreenLight, modifier = Modifier.padding(vertical = 24.dp)
                ) {
                    Text(
                        text = "Ваш индекс массы тела:",
                        style = BMITypography.Title.copy(fontSize = 16.sp)
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                        text = "%.2f".format(it.bmi),
                        style = BMITypography.Title.copy(fontSize = 24.sp)
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.recommendation,
                        style = BMITypography.Complementary
                    )
                }
            }
        }
    }
}