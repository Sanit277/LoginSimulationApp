package com.example.loginsimulationapp.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DashboardTab(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : DashboardTab("dash_home", "Home", Icons.Default.Home)
    data object Profile : DashboardTab("dash_profile", "Profile", Icons.Default.Person)
    data object Cart : DashboardTab("dash_cart", "Cart", Icons.Default.ShoppingCart)
}

val dashboardTabs = listOf(
    DashboardTab.Home,
    DashboardTab.Profile,
    DashboardTab.Cart
)
