package com.example.loginsimulationapp.ui.cart

import com.example.loginsimulationapp.ui.home.Product

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)


