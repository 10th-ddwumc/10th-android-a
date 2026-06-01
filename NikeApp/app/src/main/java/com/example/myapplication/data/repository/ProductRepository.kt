package com.example.myapplication.data.repository

import com.example.myapplication.R
import com.example.myapplication.data.model.HomeProduct
import com.example.myapplication.data.model.ShopProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository {
    fun getHomeProducts(): Flow<List<HomeProduct>> = flow {
        emit(
            listOf(
                HomeProduct(R.drawable.product1, "나이키 보메로 프리미엄", 149000),
                HomeProduct(R.drawable.product2, "잉글랜드 2026 스타디움 홈", 135000),
                HomeProduct(R.drawable.product3, "조던 컴포트 에라", 149000),
            )
        )
    }

    fun getShopProducts(): Flow<List<ShopProduct>> = flow {
        emit(
            listOf(
                ShopProduct("신제품", R.drawable.product1, "나이키 보메로 프리미엄", "여성 로드 러닝화", 3, 149000),
                ShopProduct("신제품", R.drawable.product2, "잉글랜드 2026 스타디움 홈", "남성 나이키 드라이 핏 축구 레플리카 저지", 2, 135000),
                ShopProduct("베스트셀러", R.drawable.product3, "조던 컴포트 에라", "남성 신발", 5, 149000),
            )
        )
    }

    fun getWishlistProducts(): Flow<List<ShopProduct>> = flow {
        emit(
            listOf(
                ShopProduct("신제품", R.drawable.product1, "나이키 보메로 프리미엄", "여성 로드 러닝화", 3, 149000, isLiked = true),
                ShopProduct("신제품", R.drawable.product2, "잉글랜드 2026 스타디움 홈", "남성 나이키 드라이 핏 축구 레플리카 저지", 2, 135000, isLiked = true),
                ShopProduct("베스트셀러", R.drawable.product3, "조던 컴포트 에라", "남성 신발", 5, 149000, isLiked = true),
            )
        )
    }
}
