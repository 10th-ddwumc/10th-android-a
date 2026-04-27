package com.example.umc_assignment2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/users/1")
    suspend fun getUserOne(): Response<UserResponse<UserData>>

    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int = 1
    ): Response<UserResponse<List<UserData>>>
}