package com.example.bodymassindexcalculator.data.models.repository

import com.example.bodymassindexcalculator.data.models.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val supabase: SupabaseClient
) {

    suspend fun register(inputEmail: String, inputPassword: String, firstName: String, lastName: String): Result<Unit> {
        return try {
            supabase.auth.signUpWith(Email) {
                email = inputEmail
                password = inputPassword
                data = buildJsonObject {
                    put("first_name", firstName)
                    put("last_name", lastName)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(inputEmail: String, inputPassword: String): Result<Unit> {
        return try {
            supabase.auth.signInWith(Email) {
                email = inputEmail
                password = inputPassword
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(): User {
        val userInfo = supabase.auth.retrieveUserForCurrentSession()

        return User(
            email = userInfo.email ?: "",
            firstName = userInfo.userMetadata?.get("first_name")?.jsonPrimitive?.content ?: "",
            lastName = userInfo.userMetadata?.get("last_name")?.jsonPrimitive?.content ?: ""
        )
    }
}