package com.example.umc_assignment2.repository

import retrofit2.Response

interface UserRepository {
    suspend fun getUserOne(): Response<UserResponse<UserData>>
    suspend fun getUserList(page: Int): Response<UserResponse<List<UserData>>>
}