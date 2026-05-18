package com.example.umc_assignment2

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: String,
    val category: String = "전체"
)