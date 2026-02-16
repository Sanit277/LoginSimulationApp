package com.example.loginsimulationapp.ui.cart

data class CartUiState(
    val items: List<CartItem> = emptyList()
) {
    val totalItems: Int = items.sumOf { it.quantity }
    // If your Product.price is a String like "$9.99", we’ll keep total as display for now
}
