package com.example.loginsimulationapp.ui.cart

import com.example.loginsimulationapp.data.remote.dto.ProductDto

data class CartItem(
    val product: ProductDto,
    val quantity: Int = 1
)
