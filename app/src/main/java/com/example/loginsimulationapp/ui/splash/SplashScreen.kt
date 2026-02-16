package com.example.loginsimulationapp.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loginsimulationapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {
    // Start animation immediately (no unused assignment)
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 700),
        label = "scale"
    )

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 700),
        label = "alpha"
    )

    val taglineAlpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 600, delayMillis = 250),
        label = "taglineAlpha"
    )

    LaunchedEffect(Unit) {
        delay(5000)
        onFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.hamrostorelogo),
                contentDescription = "HamroStore Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale)
                    .alpha(alpha)
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = "HamroStore",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(taglineAlpha)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Fast • Local • Reliable",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(taglineAlpha)
            )

            Spacer(Modifier.height(18.dp))

            CircularProgressIndicator(
                modifier = Modifier
                    .size(26.dp)
                    .alpha(taglineAlpha),
                strokeWidth = 3.dp
            )
        }
    }
}
