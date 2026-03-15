package com.example.loginsimulationapp.ui.delivery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DeliveryRoute(
    onLogout: () -> Unit,
    viewModel: DeliveryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    DeliveryScreen(
        uiState = uiState,
        onMarkPickedUp = viewModel::markPickedUp,
        onMarkDelivered = viewModel::markDelivered,
        onLogout = onLogout
    )
}
