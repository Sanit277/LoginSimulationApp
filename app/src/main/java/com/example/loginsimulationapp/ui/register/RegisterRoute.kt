package com.example.loginsimulationapp.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val viewModel: RegisterViewModel = viewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(state.isRegistered) {
        if (state.isRegistered) {
            onRegisterSuccess()
        }
    }

    RegisterScreen(
        fullName = state.fullName,
        email = state.email,
        phone = state.phone,
        password = state.password,
        confirmPassword = state.confirmPassword,
        role = state.role,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        successMessage = state.successMessage,
        onFullNameChange = viewModel::onFullNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPhoneChange = viewModel::onPhoneChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRoleChange = viewModel::onRoleChange,
        onRegisterClicked = viewModel::onRegisterClicked,
        onBackToLoginClicked = onBackToLogin
    )
}