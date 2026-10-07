package com.example.bodymassindexcalculator.data.models

data class BMIResult(
    val createdAt: String,
    val height: Int,
    val weight: Int,
    val bmi: Double,
    val recommendation: String
)