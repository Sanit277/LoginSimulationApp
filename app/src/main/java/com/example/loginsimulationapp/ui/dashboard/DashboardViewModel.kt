package com.example.loginsimulationapp.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState

    fun onTabChanged(route: String) {
        val tab = dashboardTabs.firstOrNull { it.route == route } ?: DashboardTab.Home
        _uiState.update { it.copy(currentTab = tab) }
    }
}
