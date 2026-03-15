package com.example.loginsimulationapp.ui.dashboard.tabs

data class ProfileUiState(
    val userName: String = "Sanit Basnet",
    val email: String = "sanit@gmail.com",
    val totalOrders: Int = 12,
    val wishlistCount: Int = 5,
    val isLoading: Boolean = false,
    val error: String? = null
)
