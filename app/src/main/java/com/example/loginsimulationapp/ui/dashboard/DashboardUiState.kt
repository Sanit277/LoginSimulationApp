package com.example.loginsimulationapp.ui.dashboard

data class DashboardUiState(
    val currentTab: DashboardTab = DashboardTab.Home
) {
    // Optional convenience getter (so your UI can read a route easily)
    val currentTabRoute: String
        get() = currentTab.route
}
