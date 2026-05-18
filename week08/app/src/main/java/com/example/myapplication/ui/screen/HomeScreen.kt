package com.example.myapplication.ui.screen

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.HomeProduct

@Composable
fun HomeScreen() {
    val products = listOf(
        HomeProduct(R.drawable.product1, "나이키 보메로 프리미엄", 149000),
        HomeProduct(R.drawable.product2, "잉글랜드 2026 스타디움 홈", 135000),
        HomeProduct(R.drawable.product3, "조던 컴포트 에라", 149000),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 17.dp, end = 17.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 50.dp)
        ) {
            Text(
                text = "Discover",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "9월 4일 목요일",
                fontSize = 16.sp,
                color = Color(0xFF767676),
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        // What's New 섹션
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp)
        ) {
            Text(
                text = "What's New",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "나이키 최신 상품",
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // 상품 리스트
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = products,
                key = { it.name }
            ) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
fun ProductItem(product: HomeProduct) {
    val windowInfo = LocalWindowInfo.current
    val itemWidth = (windowInfo.containerSize.width * 0.75f / LocalDensity.current.density).dp

    Column(
        modifier = Modifier.width(itemWidth)
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = product.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = "${product.price}원",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}