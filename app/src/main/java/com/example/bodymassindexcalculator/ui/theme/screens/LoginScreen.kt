package com.example.bodymassindexcalculator.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bodymassindexcalculator.Screen
import com.example.bodymassindexcalculator.ui.theme.BMIGrayLight
import com.example.bodymassindexcalculator.ui.theme.BMITypography
import com.example.bodymassindexcalculator.ui.theme.components.MainButton
import com.example.bodymassindexcalculator.ui.theme.components.MainCard
import com.example.bodymassindexcalculator.ui.theme.components.SecureTextInput
import com.example.bodymassindexcalculator.ui.theme.components.TextInput
import com.example.bodymassindexcalculator.ui.theme.viewmodel.LoginViewModel

@Composable
fun LoginScreen(onChangeScreen: (Screen) -> Unit) {

    val viewModel: LoginViewModel = viewModel()
    val errorText by viewModel.errorText.collectAsState()

    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BMIGrayLight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вход в приложение",
            style = BMITypography.Title.copy(fontSize = 24.sp)
        )
        MainCard(
            Modifier
                .fillMaxHeight(0.35f)
                .padding(vertical = 24.dp)
        ) {
            TextInput(
                label = "Email",
                placeholder = "example@google.com",
                textState = emailState,
                modifier = Modifier.padding(top = 12.dp)
            )
            SecureTextInput(
                label = "Пароль",
                placeholder = "Введите пароль",
                textState = passwordState
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChangeScreen(Screen.Registration) },
                text = "Зарегистрироваться",
                style = BMITypography.Title.copy(fontSize = 16.sp)
            )
        }
        MainButton("ВОЙТИ") {
            viewModel.login(
                email = emailState.text.toString(),
                password = passwordState.text.toString(),
                onChangeScreen = onChangeScreen
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        errorText?.let {
            Text(
                text = it,
                style = BMITypography.Error
            )
        }
    }
}