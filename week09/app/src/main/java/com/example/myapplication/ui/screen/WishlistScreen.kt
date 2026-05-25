package com.example.myapplication.ui.screen

import com.example.myapplication.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.ShopProduct

@Composable
fun WishlistScreen() {
    val wishlist = listOf(
        ShopProduct("신제품", R.drawable.product1, "나이키 보메로 프리미엄", "여성 로드 러닝화", 3, 149000, isLiked = true),
        ShopProduct("신제품", R.drawable.product2, "잉글랜드 2026 스타디움 홈", "남성 나이키 드라이 핏 축구 레플리카 저지", 2, 135000, isLiked = true),
        ShopProduct("베스트셀러", R.drawable.product3, "조던 컴포트 에라", "남성 신발", 5, 149000, isLiked = true),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .padding(top = 34.dp + 16.dp)
                .padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = wishlist,
                key = { it.name }
            ) { product ->
                WishlistProductItem(product = product)
            }
        }
    }
}

@Composable
fun WishlistProductItem(product: ShopProduct) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = product.description,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = "${product.color} colours",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = "₩${product.price}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}