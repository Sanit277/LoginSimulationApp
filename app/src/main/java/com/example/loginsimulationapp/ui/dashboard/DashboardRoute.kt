package com.example.loginsimulationapp.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

@Composable
fun DashboardRoute(
    onLogout: () -> Unit,
    viewModel: DashboardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    DashboardScreen(
        uiState = uiState,
        onLogout = onLogout,
        onTabRouteChanged = viewModel::onTabChanged
    )
}
