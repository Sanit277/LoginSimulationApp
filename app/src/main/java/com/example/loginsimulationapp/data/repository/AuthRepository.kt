package com.example.loginsimulationapp.data.repository

import com.example.loginsimulationapp.data.remote.RetrofitClient
import com.example.loginsimulationapp.data.remote.dto.LoginRequest
import com.example.loginsimulationapp.data.remote.dto.RegisterRequest

class AuthRepository {

    suspend fun register(
        fullName: String,
        email: String?,
        phone: String?,
        password: String,
        role: String = "CUSTOMER"
    ): Result<String> {
        return try {
            val response = RetrofitClient.authApiService.register(
                RegisterRequest(
                    fullName = fullName,
                    email = email,
                    phone = phone,
                    password = password,
                    role = role
                )
            )

            if (response.success) {
                Result.success(response.message)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }

    suspend fun login(
        identifier: String,
        password: String
    ): Result<String> {
        return try {
            val response = RetrofitClient.authApiService.login(
                LoginRequest(
                    identifier = identifier,
                    password = password
                )
            )

            if (response.success) {
                Result.success(response.message)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }
}