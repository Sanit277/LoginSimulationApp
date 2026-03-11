package com.example.loginsimulationapp.ui.register

data class RegisterState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val role: String = "CUSTOMER",
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)