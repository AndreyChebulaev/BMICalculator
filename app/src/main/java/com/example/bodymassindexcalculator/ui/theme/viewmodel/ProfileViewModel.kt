package com.example.bodymassindexcalculator.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodymassindexcalculator.data.models.BMIResult
import com.example.bodymassindexcalculator.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _user = MutableStateFlow(User("","",""))
    val user: StateFlow<User> = _user

    private val _results = MutableStateFlow<List<BMIResult>>(emptyList())
    val results: StateFlow<List<BMIResult>> = _results

    init {
        getUser()
        getResults()
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = User("ivan@mail.ru","Иван","Иванов")
            _user.value = user
        }
    }

    private fun getResults() {
        viewModelScope.launch {
            val results = listOf(
                BMIResult("13.07.2025, 01:00", 182, 62, 18.50, "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!"),
                BMIResult("13.07.2025, 01:00", 182, 62, 18.50, "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!"),
                BMIResult("13.07.2025, 01:00", 182, 62, 18.50, "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!"),
                BMIResult("13.07.2025, 01:00", 182, 62, 18.50, "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!"),
                BMIResult("13.07.2025, 01:00", 182, 62, 18.50, "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!"),
                BMIResult("13.07.2025, 01:00", 182, 62, 18.50, "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!")
            )
            _results.value = results
        }
    }
}