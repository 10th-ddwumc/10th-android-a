package com.example.umc_assignment2

import com.google.gson.annotations.SerializedName

// 서버 응답 전체를 감싸는 그릇
data class UserResponse<T>(
    val data: T
)

// 유저 한 명의 정보를 담는 그릇
data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)