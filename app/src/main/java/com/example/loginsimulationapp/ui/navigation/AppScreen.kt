package com.example.loginsimulationapp.ui.navigation

sealed class AppScreen(
    val route: String,
    val title: String
) {
//    data object Login : AppScreen(AppRoute.Login.route, "Login")
    data object Dashboard : AppScreen(AppRoute.Dashboard.route, "Dashboard")
}
