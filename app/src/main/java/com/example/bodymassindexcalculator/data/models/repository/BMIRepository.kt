package com.example.bodymassindexcalculator.data.models.repository

import com.example.bodymassindexcalculator.data.models.BMIResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.datetime.Clock
import javax.inject.Inject

class BMIRepository @Inject constructor(
    private val supabase: SupabaseClient
) {

    suspend fun saveResult(height: Int, weight: Int, bmi: Double, recommendation: String): Result<BMIResult> {
        return try {
            val userInfo = supabase.auth.retrieveUserForCurrentSession()

            val bmiResult = BMIResult(
                userId = userInfo.id,
                height = height,
                weight = weight,
                bmi = bmi,
                recommendation = recommendation,
                createdAt = Clock.System.now()
            )
            supabase.from("body_mass_index_calculations").insert(bmiResult)

            Result.success(bmiResult)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getResults(): Result<List<BMIResult>> {
        return try {
            val userInfo = supabase.auth.retrieveUserForCurrentSession()

            val response = supabase.from("body_mass_index_calculations")
                .select {
                    filter {
                        eq("user_id", userInfo.id)
                    }
                    order(column = "created_at", order = Order.DESCENDING)
                }
                .decodeList<BMIResult>()

            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}