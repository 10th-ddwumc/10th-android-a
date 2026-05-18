package com.example.umc_assignment2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.umc_assignment2.WishItem
import com.example.umc_assignment2.viewmodel.UserViewModel

@Composable
fun ShopScreen() {
    val viewModel: UserViewModel = hiltViewModel()
    val products by viewModel.shopProducts.collectAsState(initial = emptyList())

    val tabs = listOf("전체", "Tops & T-shirts", "Sale")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = Color.Black,
            edgePadding = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }

        val filteredProducts = when (selectedTabIndex) {
            0 -> products
            1 -> products.filter { it.category == "Tops" }
            2 -> products.filter { it.category == "Sale" }
            else -> products
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = filteredProducts,
                key = { it.id }
            ) { product ->
                ProductItem(
                    product = product,
                    onItemClick = { },
                    onWishClick = { p ->
                        viewModel.toggleWish(WishItem(p.name, p.price, p.imageRes))
                    },
                    showWishButton = true,
                    isGrid = true
                )
            }
        }
    }
}