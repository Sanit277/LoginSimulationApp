package com.example.loginsimulationapp.ui.signup

import androidx.compose.runtime.Composable
import com.example.loginsimulationapp.ui.register.RegisterRoute

@Composable
fun SignUpRoute(
    onSignUpSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    // Reusing RegisterRoute as it provides the registration functionality
    RegisterRoute(
        onRegisterSuccess = onSignUpSuccess,
        onBackToLogin = onLoginClick
    )
}
