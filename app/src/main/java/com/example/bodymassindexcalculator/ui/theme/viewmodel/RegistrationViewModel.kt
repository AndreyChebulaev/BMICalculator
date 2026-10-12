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
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

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
                authRepository.register(email, password, firstName, lastName)
                    .fold(
                        onSuccess = {
                            onChangeScreen(Screen.Login)
                        },
                        onFailure = {
                            _errorText.value = it.message
                        }
                    )
            }
        }
    }
}