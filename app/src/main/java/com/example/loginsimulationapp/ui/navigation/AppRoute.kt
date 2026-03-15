package com.example.loginsimulationapp.ui.navigation

sealed class AppRoute(val route: String) {
    data object Splash : AppRoute("splash")
    data object Login : AppRoute("login")
    data object Register : AppRoute("register")
    data object Dashboard : AppRoute("dashboard")
    data object AdminDashboard : AppRoute("admin_dashboard")
    data object VendorDashboard : AppRoute("vendor_dashboard")
    data object DeliveryDashboard : AppRoute("delivery_dashboard")

    data object Checkout : AppRoute("checkout")

    data object OrderSuccess : AppRoute("order_success")
    
    // Nested routes for Dashboard
    data object ProductDetail : AppRoute("product_detail/{productId}") {
        fun createRoute(productId: Long) = "product_detail/$productId"
    }
}
