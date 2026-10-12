package com.example.bodymassindexcalculator.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodymassindexcalculator.data.models.BMIResult
import com.example.bodymassindexcalculator.data.models.User
import com.example.bodymassindexcalculator.data.models.repository.AuthRepository
import com.example.bodymassindexcalculator.data.models.repository.BMIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val bmiRepository: BMIRepository,
) : ViewModel() {

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
            _user.value = authRepository.getUser()
        }
    }

    fun getResults() {
        viewModelScope.launch {
            _results.value = bmiRepository.getResults().getOrNull() ?: emptyList()
        }
    }
}