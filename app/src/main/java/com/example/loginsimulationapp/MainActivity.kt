package com.example.loginsimulationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.loginsimulationapp.ui.LoginSimulationApp
import com.example.loginsimulationapp.ui.theme.LoginSimulationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LoginSimulationAppTheme {
                LoginSimulationApp()
            }
        }
    }
}
