package com.example.loginsimulationapp.ui.navigation

sealed class AppRoute(val route: String) {
    data object Splash : AppRoute("splash")
    data object Login : AppRoute("login")

    data object Register : AppRoute("register")

    data object Dashboard : AppRoute("dashboard")
}
