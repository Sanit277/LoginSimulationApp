package com.example.loginsimulationapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val fullName: String,
    val email: String? = null,
    val phone: String? = null,
    val password: String,
    val role: String = "CUSTOMER"
)