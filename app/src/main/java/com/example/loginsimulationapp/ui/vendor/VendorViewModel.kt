package com.example.loginsimulationapp.ui.vendor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VendorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VendorUiState())
    val uiState: StateFlow<VendorUiState> = _uiState.asStateFlow()

    fun removeProduct(productId: String) {
        _uiState.update { state ->
            state.copy(
                removedProductIds = state.removedProductIds + productId,
                totalProducts = (state.totalProducts - 1).coerceAtLeast(0)
            )
        }
    }

    fun markOrderFulfilled(orderId: String) {
        _uiState.update { state ->
            state.copy(
                fulfilledOrderIds = state.fulfilledOrderIds + orderId,
                pendingOrders = (state.pendingOrders - 1).coerceAtLeast(0)
            )
        }
    }
}
