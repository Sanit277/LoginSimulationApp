package com.example.loginsimulationapp.data.remote.dto

import androidx.annotation.DrawableRes

data class ProductDto(
    val id: Long,
    val storeId: Long,
    val storeName: String,
    val categoryId: Long,
    val categoryName: String,
    val productName: String,
    val productDescription: String,
    val price: Double,
    val stockQuantity: Int,
    val brand: String,
    @DrawableRes val imageRes: Int,
    val status: String
)
