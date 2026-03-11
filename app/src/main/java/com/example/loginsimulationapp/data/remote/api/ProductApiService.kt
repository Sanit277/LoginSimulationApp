package com.example.loginsimulationapp.data.remote.api

import com.example.loginsimulationapp.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    @GET("api/products")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("api/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Long
    ): ProductDto
}