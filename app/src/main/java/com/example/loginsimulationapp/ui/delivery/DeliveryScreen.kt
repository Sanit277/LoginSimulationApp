package com.example.loginsimulationapp.ui.delivery

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryScreen(
    uiState: DeliveryUiState,
    onMarkPickedUp: (String) -> Unit,
    onMarkDelivered: (String) -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Delivery Dashboard",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Welcome
            item {
                Text(
                    text = "Welcome, ${uiState.agentName}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp
                )
            }

            // Stats Row
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DeliveryStatCard(
                        label = "Active Deliveries",
                        value = uiState.activeDeliveries.toString(),
                        modifier = Modifier.weight(1f),
                        highlight = uiState.activeDeliveries > 0
                    )
                    DeliveryStatCard(
                        label = "Completed Today",
                        value = uiState.completedToday.toString(),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                DeliveryStatCard(
                    label = "Today's Earnings (NPR)",
                    value = "Rs. ${"%,.0f".format(uiState.totalEarnings)}",
                    modifier = Modifier.fillMaxWidth(),
                    isWide = true
                )
            }

            // Deliveries List
            item {
                Text(
                    text = "My Deliveries",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(uiState.deliveries) { delivery ->
                val isPickedUp = delivery.orderId in uiState.pickedUpIds || delivery.status == "Picked Up"
                val isDelivered = delivery.orderId in uiState.deliveredIds || delivery.status == "Delivered"

                val backgroundColor = if (isDelivered) Color(0xFFE8F5E9) else Color(0xFFF8F8F8)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = delivery.orderId,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            
                            AnimatedContent(
                                targetState = when {
                                    isDelivered -> "Delivered ✓"
                                    isPickedUp -> "Picked Up"
                                    else -> "Pending"
                                },
                                transitionSpec = {
                                    (fadeIn() + scaleIn()).togetherWith(fadeOut() + scaleOut())
                                },
                                label = "StatusText"
                            ) { targetText ->
                                val chipColor = when (targetText) {
                                    "Delivered ✓" -> Color(0xFF2E7D32)
                                    "Picked Up" -> Color(0xFF1565C0)
                                    else -> Color(0xFFE65100)
                                }
                                Surface(
                                    color = chipColor.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = targetText,
                                        color = chipColor,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                        
                        Text(
                            text = delivery.customerName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        
                        Row(verticalAlignment = Alignment.Top) {
                            Text("📦", fontSize = 14.sp)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = delivery.product,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        
                        Row(verticalAlignment = Alignment.Top) {
                            Text("📍", fontSize = 14.sp)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = delivery.address,
                                fontSize = 14.sp,
                                color = Color.Gray,
                                lineHeight = 18.sp
                            )
                        }

                        if (!isDelivered) {
                            Spacer(Modifier.height(4.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (!isPickedUp) {
                                    Button(
                                        onClick = { onMarkPickedUp(delivery.orderId) },
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Black,
                                            contentColor = Color.White
                                        ),
                                        modifier = Modifier.weight(1f).height(40.dp)
                                    ) {
                                        Text("Pick Up", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Button(
                                    onClick = { onMarkDelivered(delivery.orderId) },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White,
                                        disabledContainerColor = Color.LightGray
                                    ),
                                    modifier = Modifier.weight(1f).height(40.dp),
                                    enabled = isPickedUp
                                ) {
                                    Text("Deliver", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeliveryStatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    highlight: Boolean = false,
    isWide: Boolean = false
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (highlight) Color.Black else Color(0xFFF0F0F0)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(if (isWide) 20.dp else 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontSize = if (isWide) 28.sp else 24.sp,
                fontWeight = FontWeight.Black,
                color = if (highlight) Color.White else Color.Black
            )
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (highlight) Color.White.copy(alpha = 0.7f) else Color.Gray
            )
        }
    }
}
