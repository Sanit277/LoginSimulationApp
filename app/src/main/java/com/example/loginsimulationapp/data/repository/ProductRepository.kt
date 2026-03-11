package com.example.loginsimulationapp.data.repository

import com.example.loginsimulationapp.data.remote.RetrofitClient
import com.example.loginsimulationapp.data.remote.api.ProductApiService
import com.example.loginsimulationapp.data.remote.dto.ProductDto

class ProductRepository(
    private val productApiService: ProductApiService = RetrofitClient.productApiService
) {
    suspend fun getAllProducts(): List<ProductDto> {
        return productApiService.getAllProducts()
    }

    suspend fun getProductById(id: Long): ProductDto {
        return productApiService.getProductById(id)
    }
}