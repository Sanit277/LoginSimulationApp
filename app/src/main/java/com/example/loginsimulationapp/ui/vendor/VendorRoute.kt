package com.example.loginsimulationapp.ui.vendor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VendorRoute(
    onLogout: () -> Unit,
    viewModel: VendorViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorScreen(
        uiState = uiState,
        onRemoveProduct = viewModel::removeProduct,
        onMarkOrderFulfilled = viewModel::markOrderFulfilled,
        onLogout = onLogout
    )
}
