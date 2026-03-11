package com.example.loginsimulationapp.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
private fun CartItemRow(
    item: CartItem,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ Use AsyncImage for ProductDto imageUrl
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = item.product.imageUrl,
                    contentDescription = item.product.productName,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.product.productName,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Rs ${item.product.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.width(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onMinus) { 
                    Text("-", style = MaterialTheme.typography.titleLarge) 
                }
                Text(
                    text = "${item.quantity}",
                    modifier = Modifier.widthIn(min = 20.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = onPlus) { 
                    Text("+", style = MaterialTheme.typography.titleLarge) 
                }
            }

            Spacer(Modifier.width(6.dp))

            IconButton(onClick = onDelete) {
                Text("×", style = MaterialTheme.typography.headlineSmall, color = Color.Red)
            }
        }
    }
}

@Composable
fun CartScreen(
    uiState: CartUiState,
    onPlus: (CartItem) -> Unit,
    onMinus: (CartItem) -> Unit,
    onDelete: (CartItem) -> Unit,
    onClear: () -> Unit,
    onCheckout: () -> Unit
) {
    if (uiState.items.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Your cart is empty 🛒", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Cart Items", style = MaterialTheme.typography.titleLarge)
            TextButton(onClick = onClear) { 
                Text("Clear All", color = Color.Red) 
            }
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(uiState.items) { item ->
                CartItemRow(
                    item = item,
                    onPlus = { onPlus(item) },
                    onMinus = { onMinus(item) },
                    onDelete = { onDelete(item) }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = onCheckout,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Checkout (${uiState.totalItems} items)", modifier = Modifier.padding(8.dp))
        }
    }
}
