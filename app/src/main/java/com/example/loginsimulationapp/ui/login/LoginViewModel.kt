package com.example.loginsimulationapp.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update{
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }
    fun onLoginClicked() {
        _state.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
        // Simulated login logic
        if (_state.value.email == "test@example.com" &&
            _state.value.password == "123456"
        ) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isLoggedIn = true
                )
            }
        } else {
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = "Invalid credentials"
                )
            }
        }
    }
}
