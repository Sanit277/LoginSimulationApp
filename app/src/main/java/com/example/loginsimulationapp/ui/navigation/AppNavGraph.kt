package com.example.loginsimulationapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginsimulationapp.ui.dashboard.DashboardRoute
import com.example.loginsimulationapp.ui.login.LoginRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Login.route,
        modifier = modifier
    ) {
        composable(AppRoute.Login.route) {
            // Pass padding down to the screen content
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
