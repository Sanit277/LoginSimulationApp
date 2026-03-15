package com.example.loginsimulationapp.ui.delivery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DeliveryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DeliveryUiState())
    val uiState: StateFlow<DeliveryUiState> = _uiState.asStateFlow()

    fun markPickedUp(orderId: String) {
        _uiState.update { state ->
            state.copy(pickedUpIds = state.pickedUpIds + orderId)
        }
    }

    fun markDelivered(orderId: String) {
        _uiState.update { state ->
            state.copy(
                deliveredIds = state.deliveredIds + orderId,
                completedToday = state.completedToday + 1,
                activeDeliveries = (state.activeDeliveries - 1).coerceAtLeast(0),
                totalEarnings = state.totalEarnings + 150.0
            )
        }
    }
}
