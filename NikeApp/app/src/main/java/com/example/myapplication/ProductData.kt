package com.example.myapplication

data class Product(
    val image: Int,
    val name: String,
    val price: Int,
    val category: String? = null,
    val tag: String? = null
)