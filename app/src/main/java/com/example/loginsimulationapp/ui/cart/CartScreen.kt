package com.example.loginsimulationapp.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
private fun CartItemRow(
    item: CartItem,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ Fixed image size
            Image(
                painter = painterResource(id = item.product.imageRes),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )

            Spacer(Modifier.width(12.dp))

            // ✅ This column takes remaining space so buttons stay aligned
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.product.price,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.width(8.dp))

            // ✅ Quantity controls with fixed width behavior
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onMinus) { Text("-") }
                Text(
                    text = "${item.quantity}",
                    modifier = Modifier.widthIn(min = 20.dp),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = onPlus) { Text("+") }
            }

            Spacer(Modifier.width(6.dp))

            TextButton(onClick = onDelete) {
                Text("Remove", maxLines = 1)
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
            Text("Your cart is empty 🛒")
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
            TextButton(onClick = onClear) { Text("Clear") }
        }

        Spacer(Modifier.height(12.dp))

        // ✅ THIS is the LazyColumn you were asking about
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Checkout (${uiState.totalItems} items)")
        }
    }
}

