package com.example.loginsimulationapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsimulationapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    val roles = listOf("Admin", "Customer", "Vendor", "Delivery Agent")

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        onRoleSelected("Customer")
    }

    fun onRoleSelected(role: String) {
        val (identifier, password) = when (role) {
            "Admin" -> "admin@hamrostore.com" to "admin123"
            "Vendor" -> "vendor@hamrostore.com" to "vendor123"
            "Delivery Agent" -> "delivery@hamrostore.com" to "delivery123"
            else -> "customer@hamrostore.com" to "customer123"
        }
        _state.update {
            it.copy(
                selectedRole = role,
                identifier = identifier,
                password = password,
                isRoleDropdownExpanded = false
            )
        }
    }

    fun onRoleDropdownExpandedChange(expanded: Boolean) {
        _state.update {
            it.copy(isRoleDropdownExpanded = expanded)
        }
    }

    fun onIdentifierChange(identifier: String) {
        _state.update {
            it.copy(identifier = identifier)
        }
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun onLoginClicked() {
        val current = _state.value

        // Validation: identifier
        if (current.identifier.isBlank()) {
            _state.update { it.copy(errorMessage = "Email or phone is required") }
            return
        }
        // Basic email format check
        if (current.identifier.contains("@") && !current.identifier.matches(Regex("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$"))) {
            _state.update { it.copy(errorMessage = "Please enter a valid email address") }
            return
        }
        // Validation: password
        if (current.password.isBlank()) {
            _state.update { it.copy(errorMessage = "Password is required") }
            return
        }
        if (current.password.length < 6) {
            _state.update { it.copy(errorMessage = "Password must be at least 6 characters") }
            return
        }

        _state.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val result = repository.login(current.identifier, current.password)

            result.onSuccess {
                val simulatedRole = when {
                    current.identifier.contains("admin", ignoreCase = true) -> "ADMIN"
                    current.identifier.contains("vendor", ignoreCase = true) -> "VENDOR"
                    current.identifier.contains("delivery", ignoreCase = true) -> "DELIVERY_AGENT"
                    else -> "CUSTOMER"
                }
                // Simulate wrong-password check: expected password per role
                val expectedPassword = when (simulatedRole) {
                    "ADMIN" -> "admin123"
                    "VENDOR" -> "vendor123"
                    "DELIVERY_AGENT" -> "delivery123"
                    else -> "customer123"
                }
                if (current.password != expectedPassword) {
                    _state.update {
                        it.copy(isLoading = false, errorMessage = "Incorrect password for selected role")
                    }
                    return@launch
                }

                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        userRole = simulatedRole,
                        errorMessage = null
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Login failed. Please try again."
                    )
                }
            }
        }
    }
}
