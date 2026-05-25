package ddwu.umc.chapter02

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



val dummyShopProducts = listOf(
    Product(101, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", 5, 10, R.drawable.socks6pair),
    Product(102, "Nike Elite Crew", "Basketball Socks", 7, 16, R.drawable.socks),
    Product(103, "Nike Air Force 1 '07", "Women's Shoes", 5, 115, R.drawable.airforce107, true),
    Product(104, "Jordan ENike Air Force 1 '07ssentials", "Men's Shoes", 2, 115, R.drawable.airforce107ssentials, true)
)
@Composable
fun ShopScreen() {
    // 현재 선택된 탭을 기억하는 상태 변수
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "sale")

    // 선택된 탭에 따라 보여줄 상품 리스트를 다르게
    val filteredProducts = when (selectedTabIndex) {
        0 -> dummyShopProducts // 전체: 모든 상품 보여주기
        1 -> dummyShopProducts.filter { it.name.contains("Shirt") } // Tops & T-Shirts: 이름에 Shirt 들어간 것만 (현재는 데이터 없어서 빈 화면)
        2 -> dummyShopProducts.filter { it.price < 50 } // Sale: 가격이 50달러 이하인 양말류만 보여주기
        else -> dummyShopProducts
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = Color.Black,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color.Black // 탭 밑줄 검은색
                )
            },
            divider = {} // 기본 회색 가로줄 제거
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.Black else Color(0xFF888888)

                        )
                    }
                )
            }
        }

        // 탭 아래에 들어갈 2열 격자 리스트
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp), // 탭 아래 여백
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                items = filteredProducts,
                key = { product -> product.id }
            ) { product ->
                ProductItem(product = product)
            }
        }
    }
}