package com.example.bodymassindexcalculator.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodymassindexcalculator.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel() : ViewModel() {

    private val _errorText = MutableStateFlow<String?>(null)
    val errorText: StateFlow<String?> = _errorText

    fun register(email: String, password: String, firstName: String, lastName: String, onChangeScreen: (Screen) -> Unit) {
        viewModelScope.launch {

            if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                _errorText.value = "Заполните все поля!"
            }
            else if (password.length < 6) {
                _errorText.value = "Пароль должен содержать минимум 6 символов!"
            }
            else {
                onChangeScreen(Screen.Login)
            }
        }
    }
}