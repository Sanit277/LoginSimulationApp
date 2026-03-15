package com.example.loginsimulationapp.ui.admin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdminViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    fun approveVendor(vendorId: String) {
        _uiState.update { state ->
            state.copy(
                approvedVendorIds = state.approvedVendorIds + vendorId,
                pendingVendorApprovals = (state.pendingVendorApprovals - 1).coerceAtLeast(0)
            )
        }
    }

    fun rejectVendor(vendorId: String) {
        _uiState.update { state ->
            state.copy(
                rejectedVendorIds = state.rejectedVendorIds + vendorId,
                pendingVendorApprovals = (state.pendingVendorApprovals - 1).coerceAtLeast(0)
            )
        }
    }
}
