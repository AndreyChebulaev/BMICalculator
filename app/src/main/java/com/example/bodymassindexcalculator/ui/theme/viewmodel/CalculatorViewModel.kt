package com.example.bodymassindexcalculator.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodymassindexcalculator.data.models.BMIResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {

    private val _result = MutableStateFlow<BMIResult?>(null)
    val result: StateFlow<BMIResult?> = _result

    private val _errorText = MutableStateFlow<String?>(null)
    val errorText: StateFlow<String?> = _errorText

    fun calculateResult(height: String, weight: String) {
        viewModelScope.launch {
            if (height.isEmpty() && weight.isEmpty()) {
                _errorText.value = "Заполните все поля!"
            }
            else if (height.isEmpty()) {
                _errorText.value = "Поле Рост не заполнено!"
            }
            else if (weight.isEmpty()) {
                _errorText.value = "Поле Вес не заполнено!"
            }
            else {
                val height = height.toInt()
                val weight = weight.toInt()
                val heightM: Double = height / 100.0
                val bmi = weight / (heightM * heightM)
                val recommendation = when {
                    bmi <= 16.0 -> "Выраженный дефицит массы тела. Советуем набрать вес для здоровья."
                    bmi > 16.0 && bmi <= 18.5 -> "Недостаточная масса тела. Рекомендуется увеличить массу тела."
                    bmi > 18.5 && bmi <= 24.99 -> "Норма. Ваш вес в здоровом диапазоне - поддерживайте его!"
                    bmi >= 25.0 && bmi < 30.0 -> "Избыточная масса тела или предожирение. Желательно снизить вес для улучшения."
                    bmi >= 30.0 && bmi < 35.0 -> "Ожирение. Рекомендуется уменьшить вес под контролем специалиста."
                    bmi >= 35.0 && bmi < 40.0 -> "Ожирение резкое. Необходимо снижение веса с медицинской поддержкой."
                    else -> "Очень резкое ожирение. Требуется срочная коррекция веса под наблюдением врача."
                }
                _result.value = BMIResult(
                    createdAt = "13.07.2025, 01:00",
                    height = height,
                    weight = weight,
                    bmi = bmi,
                    recommendation = recommendation
                )
            }
        }
    }
}
