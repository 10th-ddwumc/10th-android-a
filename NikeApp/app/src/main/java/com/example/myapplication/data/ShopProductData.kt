package com.example.myapplication.data

data class ShopProduct(
    val tag: String? = null,
    val imageResId: Int,
    val name: String,
    val description: String,
    val color: Int,
    val price: Int,
    var isLiked: Boolean = false
)