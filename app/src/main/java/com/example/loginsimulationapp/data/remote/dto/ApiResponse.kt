package com.example.loginsimulationapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String
)