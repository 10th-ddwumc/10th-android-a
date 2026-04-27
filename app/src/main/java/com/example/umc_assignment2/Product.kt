package com.example.umc_assignment2

data class Product(
    val name: String,
    val price: String,
    val imageRes: Int,
    val category: String = "전체"
)