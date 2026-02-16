package com.example.loginsimulationapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsimulationapp.R

@Composable
fun LoginScreen(
    email: String,
    password: String,
    isLoading: Boolean,
    errorMessage: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClicked: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.hamrostorelogo),
            contentDescription = "App Logo",
            modifier = Modifier.width(200.dp).height(100.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Welcome back!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(text = "Email")
            },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if(errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(12.dp)
            )
        }

        Button(
            onClick = onLoginClicked,
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Black.copy(alpha = 0.5f),
                disabledContentColor = Color.White.copy(alpha = 0.7f)
            ),
            modifier = Modifier
                .width(300.dp)
                .height(48.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White // makes loader visible on black
                )
            } else {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Forgot Password ?",
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Don't have an account? Sign up",
            color = Color.Gray,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(
        email = "",
        password = "",
        isLoading = false,
        errorMessage = null,
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClicked = {}
    )
}