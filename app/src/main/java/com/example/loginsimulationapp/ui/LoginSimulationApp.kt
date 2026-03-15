package com.example.loginsimulationapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.loginsimulationapp.ui.navigation.AppNavigation

@Composable
fun LoginSimulationApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // Removed the outer Scaffold to prevent double-padding/spacing issues
    // with the system bars and nested Scaffolds (like in DashboardScreen).
    AppNavigation(
        navController = navController,
        modifier = modifier
    )
}
