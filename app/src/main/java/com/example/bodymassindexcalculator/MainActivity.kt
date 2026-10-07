package com.example.bodymassindexcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bodymassindexcalculator.ui.theme.screens.CalculatorScreen
import com.example.bodymassindexcalculator.ui.theme.screens.LoginScreen
import com.example.bodymassindexcalculator.ui.theme.screens.ProfileScreen
import com.example.bodymassindexcalculator.ui.theme.screens.RegistrationScreen
import dagger.hilt.android.AndroidEntryPoint

enum class Screen {
    Login, Registration, Calculator, Profile
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf(Screen.Login)}
            val changeScreen = {value: Screen -> screen = value}

            when(screen) {
                Screen.Profile -> ProfileScreen(changeScreen)
                Screen.Login -> LoginScreen(changeScreen)
                Screen.Registration -> RegistrationScreen(changeScreen)
                Screen.Calculator -> CalculatorScreen(changeScreen)
            }
        }
    }
}