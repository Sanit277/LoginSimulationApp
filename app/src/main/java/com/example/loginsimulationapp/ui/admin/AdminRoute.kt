package com.example.loginsimulationapp.ui.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AdminRoute(
    onLogout: () -> Unit,
    viewModel: AdminViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AdminScreen(
        uiState = uiState,
        onApproveVendor = viewModel::approveVendor,
        onRejectVendor = viewModel::rejectVendor,
        onLogout = onLogout
    )
}
