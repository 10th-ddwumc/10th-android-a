package com.example.myapplication.data.network

import com.example.myapplication.data.model.UserDetailResponse
import com.example.myapplication.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

// API 요청 가능한 것 정의
interface ApiService {
    @GET("users?page=1")
    suspend fun getUsers(
        @Header("x-api-key") apiKey: String
    ): Response<UserResponse>

    @GET("users/{id}")
    suspend fun getUser(
        @Header("x-api-key") apiKey: String,
        @Path("id") id: Int
    ): Response<UserDetailResponse>
}