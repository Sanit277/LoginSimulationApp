package com.example.loginsimulationapp.data.remote.api

import com.example.loginsimulationapp.data.remote.dto.ApiResponse
import com.example.loginsimulationapp.data.remote.dto.LoginRequest
import com.example.loginsimulationapp.data.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): ApiResponse

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): ApiResponse
}