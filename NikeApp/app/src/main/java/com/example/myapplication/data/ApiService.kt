package com.example.myapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

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