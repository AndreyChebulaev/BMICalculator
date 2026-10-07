package com.example.bodymassindexcalculator.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodymassindexcalculator.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private val _errorText = MutableStateFlow<String?>(null)
    val errorText: StateFlow<String?> = _errorText

    fun login(email: String, password: String, onChangeScreen: (Screen) -> Unit) {
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty()) {
                _errorText.value = "Заполните все поля!"
            }
            else {
                onChangeScreen(Screen.Calculator)
            }
        }
    }
}