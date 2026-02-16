package com.example.loginsimulationapp.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit
) {
    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    // 🔹 Navigation side-effect
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLoginSuccess()
        }
    }

    LoginScreen(
        email = state.email,
        password = state.password,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClicked = viewModel::onLoginClicked
    )
}
