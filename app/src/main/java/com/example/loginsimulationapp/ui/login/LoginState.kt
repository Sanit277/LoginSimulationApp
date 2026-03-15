package com.example.loginsimulationapp.ui.login

data class LoginState(
    val selectedRole: String = "Customer",
    val isRoleDropdownExpanded: Boolean = false,
    val identifier: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val userRole: String? = null // Store role after login
)