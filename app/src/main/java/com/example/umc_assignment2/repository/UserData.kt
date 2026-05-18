package com.example.umc_assignment2.repository // 본인 패키지명에 맞춰주세요!

import com.google.gson.annotations.SerializedName

data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)