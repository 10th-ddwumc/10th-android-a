package com.example.myapplication.data.repository

import com.example.myapplication.data.network.ApiService
import com.example.myapplication.data.model.UserDetailResponse
import com.example.myapplication.data.model.UserResponse
import retrofit2.Response


// 데이터를 어떻게 가져올 건지 결정
class UserRepository(private val apiService: ApiService) {
    suspend fun getUser(apiKey: String, id: Int): Response<UserDetailResponse> {
        return apiService.getUser(apiKey, id)
    }

    suspend fun getUsers(apiKey: String): Response<UserResponse> {
        return apiService.getUsers(apiKey)
    }
}
