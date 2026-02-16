package com.example.loginsimulationapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
    Scaffold { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = modifier.padding(innerPadding)
        )
    }
}
