package com.example.loginsimulationapp.data.repository

import kotlinx.coroutines.delay

class AuthRepository {

    suspend fun register(
        fullName: String,
        email: String?,
        phone: String?,
        password: String,
        role: String = "CUSTOMER"
    ): Result<String> {
        delay(1000) // Simulate network delay
        // Simulate local validation or success
        return if (fullName.isNotBlank() && password.length >= 6) {
            Result.success("Registration successful")
        } else {
            Result.failure(Exception("Registration failed: Invalid input"))
        }
    }

    suspend fun login(
        identifier: String,
        password: String
    ): Result<String> {
        delay(1000) // Simulate network delay
        // Simulate local success
        return if (identifier.isNotBlank() && password.isNotBlank()) {
            Result.success("Login successful")
        } else {
            Result.failure(Exception("Login failed: Invalid credentials"))
        }
    }
}