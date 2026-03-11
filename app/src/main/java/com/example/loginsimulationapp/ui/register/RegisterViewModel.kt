package com.example.loginsimulationapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsimulationapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun onFullNameChange(value: String) {
        _state.update { it.copy(fullName = value) }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPhoneChange(value: String) {
        _state.update { it.copy(phone = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onConfirmPasswordChange(value: String) {
        _state.update { it.copy(confirmPassword = value) }
    }

    fun onRoleChange(value: String) {
        _state.update { it.copy(role = value) }
    }

    fun onRegisterClicked() {
        val current = _state.value

        val hasEmail = current.email.isNotBlank()
        val hasPhone = current.phone.isNotBlank()

        when {
            current.fullName.isBlank() -> {
                _state.update { it.copy(errorMessage = "Full name is required", successMessage = null) }
                return
            }

            !hasEmail && !hasPhone -> {
                _state.update { it.copy(errorMessage = "Email or phone is required", successMessage = null) }
                return
            }

            hasEmail && !current.email.contains("@") -> {
                _state.update { it.copy(errorMessage = "Please enter a valid email", successMessage = null) }
                return
            }

            current.password.length < 6 -> {
                _state.update { it.copy(errorMessage = "Password must be at least 6 characters", successMessage = null) }
                return
            }

            current.password != current.confirmPassword -> {
                _state.update { it.copy(errorMessage = "Passwords do not match", successMessage = null) }
                return
            }
        }

        _state.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )
        }

        viewModelScope.launch {
            val result = repository.register(
                fullName = current.fullName,
                email = current.email.ifBlank { null },
                phone = current.phone.ifBlank { null },
                password = current.password,
                role = current.role
            )

            result.onSuccess { message ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        successMessage = message,
                        errorMessage = null,
                        isRegistered = true
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Registration failed"
                    )
                }
            }
        }
    }
}