package com.example.umc_assignment2.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.umc_assignment2.R
import com.example.umc_assignment2.WishItem
import com.example.umc_assignment2.viewmodel.UserViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val tabs = listOf(
        Triple(AppDestination.Home, "홈", R.drawable.housesimple),
        Triple(AppDestination.Shop, "구매하기", R.drawable.listmagnifyingglass),
        Triple(AppDestination.Wishlist, "위시리스트", R.drawable.heartstraight),
        Triple(AppDestination.Cart, "장바구니", R.drawable.bagsimple),
        Triple(AppDestination.Profile, "프로필", R.drawable.user)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { (destination, label, icon) ->
                    NavigationBarItem(
                        selected = currentDestination?.route == destination::class.qualifiedName,
                        onClick = {
                            navController.navigate(destination) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = label
                            )
                        },
                        label = { Text(text = label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppDestination.Home> {
                val viewModel: UserViewModel = hiltViewModel()
                val products by viewModel.homeProducts.collectAsState(initial = emptyList())

                HomeScreen(
                    products = products,
                    onItemClick = { product ->
                    },
                    onWishClick = { product ->
                        val wishItem = WishItem(product.name, product.price, product.imageRes)
                        viewModel.toggleWish(wishItem)
                    }
                )
            }
            composable<AppDestination.Shop> { ShopScreen() }
            composable<AppDestination.Wishlist> { WishlistScreen() }
            composable<AppDestination.Cart> {
                CartScreen(
                    onOrderClick = {
                        navController.navigate(AppDestination.Shop) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable<AppDestination.Profile> { ProfileScreen() }
        }
    }
}