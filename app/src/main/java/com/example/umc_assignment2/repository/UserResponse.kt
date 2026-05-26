package com.example.umc_assignment2.repository

import com.google.gson.annotations.SerializedName

data class UserResponse<T>(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: T
)