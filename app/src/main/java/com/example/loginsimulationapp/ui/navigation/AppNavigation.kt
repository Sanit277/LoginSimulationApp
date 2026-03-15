package com.example.loginsimulationapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginsimulationapp.ui.admin.AdminRoute
import com.example.loginsimulationapp.ui.checkout.CheckoutScreen
import com.example.loginsimulationapp.ui.checkout.OrderSuccessScreen
import com.example.loginsimulationapp.ui.dashboard.DashboardScreen
import com.example.loginsimulationapp.ui.dashboard.DashboardUiState
import com.example.loginsimulationapp.ui.delivery.DeliveryRoute
import com.example.loginsimulationapp.ui.login.LoginRoute
import com.example.loginsimulationapp.ui.register.RegisterRoute
import com.example.loginsimulationapp.ui.splash.SplashScreen
import com.example.loginsimulationapp.ui.vendor.VendorRoute

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route,
        modifier = modifier
    ) {
        composable(AppRoute.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Login.route) {
            LoginRoute(
                onLoginSuccess = { role ->
                    val destination = when (role.uppercase()) {
                        "ADMIN" -> AppRoute.AdminDashboard.route
                        "VENDOR" -> AppRoute.VendorDashboard.route
                        "DELIVERY_AGENT" -> AppRoute.DeliveryDashboard.route
                        else -> AppRoute.Dashboard.route
                    }
                    navController.navigate(destination) {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate(AppRoute.Register.route)
                }
            )
        }

        composable(AppRoute.Register.route) {
            RegisterRoute(
                onRegisterSuccess = {
                    navController.popBackStack()
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // Customer Dashboard (existing, unchanged)
        composable(AppRoute.Dashboard.route) {
            DashboardScreen(
                uiState = DashboardUiState(),
                onLogout = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Dashboard.route) { inclusive = true }
                    }
                },
                onTabRouteChanged = {},
                onCheckoutClick = {
                    navController.navigate(AppRoute.Checkout.route)
                }
            )
        }

        // Admin Dashboard
        composable(AppRoute.AdminDashboard.route) {
            AdminRoute(
                onLogout = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.AdminDashboard.route) { inclusive = true }
                    }
                }
            )
        }

        // Vendor Dashboard
        composable(AppRoute.VendorDashboard.route) {
            VendorRoute(
                onLogout = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.VendorDashboard.route) { inclusive = true }
                    }
                }
            )
        }

        // Delivery Agent Dashboard
        composable(AppRoute.DeliveryDashboard.route) {
            DeliveryRoute(
                onLogout = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.DeliveryDashboard.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Checkout.route) {
            CheckoutScreen(
                onBack = { navController.popBackStack() },
                onOrderPlaced = {
                    navController.navigate(AppRoute.OrderSuccess.route) {
                        popUpTo(AppRoute.Dashboard.route)
                    }
                }
            )
        }

        composable(AppRoute.OrderSuccess.route) {
            OrderSuccessScreen(
                onGoHome = {
                    navController.navigate(AppRoute.Dashboard.route) {
                        popUpTo(AppRoute.OrderSuccess.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
