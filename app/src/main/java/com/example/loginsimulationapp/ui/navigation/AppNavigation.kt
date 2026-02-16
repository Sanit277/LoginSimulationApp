package com.example.loginsimulationapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginsimulationapp.ui.dashboard.DashboardRoute
import com.example.loginsimulationapp.ui.login.LoginRoute
import com.example.loginsimulationapp.ui.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route,   // ✅ splash first
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
                onLoginSuccess = {
                    navController.navigate(AppRoute.Dashboard.route) {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.Dashboard.route) {
            DashboardRoute(
                onLogout = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Dashboard.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
