package com.example.bodymassindexcalculator.data.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BMIResult(
    @SerialName("user_id") val userId: String,
    @SerialName("created_at") val createdAt: Instant,
    val height: Int,
    val weight: Int,
    @SerialName("body_mass_index") val bmi: Double,
    val recommendation: String
)