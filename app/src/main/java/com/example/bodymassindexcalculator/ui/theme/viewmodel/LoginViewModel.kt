package com.example.bodymassindexcalculator.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodymassindexcalculator.Screen
import com.example.bodymassindexcalculator.data.models.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _errorText = MutableStateFlow<String?>(null)
    val errorText: StateFlow<String?> = _errorText

    fun login(email: String, password: String, onChangeScreen: (Screen) -> Unit) {
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty()) {
                _errorText.value = "Заполните все поля!"
            }
            else {
                authRepository.login(email, password)
                    .fold(
                        onSuccess = {
                            onChangeScreen(Screen.Calculator)
                        },
                        onFailure = {
                            _errorText.value = it.message
                        }
                    )
            }
        }
    }
}