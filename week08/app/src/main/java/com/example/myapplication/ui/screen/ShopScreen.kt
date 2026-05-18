package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.ShopProduct

@Composable
fun ShopScreen() {
    val tabs = listOf("전체", "Tops & T-shirts", "Sale")
    var selectedTab by remember { mutableIntStateOf(0) }

    val products = listOf(
        ShopProduct("신제품", R.drawable.product1, "나이키 보메로 프리미엄", "여성 로드 러닝화", 3, 149000),
        ShopProduct("신제품", R.drawable.product2, "잉글랜드 2026 스타디움 홈", "남성 나이키 드라이 핏 축구 레플리카 저지", 2, 135000),
        ShopProduct("베스트셀러", R.drawable.product3, "조던 컴포트 에라", "남성 신발", 5, 149000),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(15.dp))

        // 탭바
        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                Text(
                    text = title,
                    color = if (selectedTab == index) Color.Black else Color.Gray,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { selectedTab = index }
                        .drawBehind {
                            if (selectedTab == index) {
                                drawLine(
                                    color = Color.Black,
                                    start = Offset(0f, size.height + 10.dp.toPx()),
                                    end = Offset(size.width, size.height + 10.dp.toPx()),
                                    strokeWidth = 2.dp.toPx()
                                )
                            }
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 탭 콘텐츠
        when (selectedTab) {
            0 -> ShopAllTab(products)
            1 -> ShopEmptyTab()
            2 -> ShopEmptyTab()
        }
    }
}

@Composable
fun ShopAllTab(products: List<ShopProduct>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = products,
            key = { it.name }
        ) { product ->
            ShopProductItem(product = product)
        }
    }
}

@Composable
fun ShopProductItem(product: ShopProduct) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            // 위시리스트 버튼
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(34.dp)
                    .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_unliked),
                    contentDescription = "위시리스트",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        product.tag?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 5.dp)
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

@Composable
fun ShopEmptyTab() {
    Box(modifier = Modifier.fillMaxSize())
}