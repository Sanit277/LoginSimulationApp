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

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

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

        when {
            current.identifier.isBlank() -> {
                _state.update { it.copy(errorMessage = "Email or phone is required") }
                return
            }

            current.password.isBlank() -> {
                _state.update { it.copy(errorMessage = "Password is required") }
                return
            }
        }

        _state.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            val result = repository.login(
                identifier = current.identifier,
                password = current.password
            )

            result.onSuccess {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        errorMessage = null
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Invalid credentials"
                    )
                }
            }
        }
    }
}