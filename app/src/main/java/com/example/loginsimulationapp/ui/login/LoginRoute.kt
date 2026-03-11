package com.example.loginsimulationapp.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLoginSuccess()
        }
    }

    LoginScreen(
        identifier = state.identifier,
        password = state.password,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        onIdentifierChange = viewModel::onIdentifierChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClicked = viewModel::onLoginClicked,
        onSignUpClicked = onSignUpClick
    )
}