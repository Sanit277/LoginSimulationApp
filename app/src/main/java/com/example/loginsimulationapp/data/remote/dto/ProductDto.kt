package com.example.loginsimulationapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
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
    val imageUrl: String,
    val status: String
)