package com.example.loginsimulationapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val identifier: String,
    val password: String
)