package com.example.myapplication.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R

sealed class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    data object Home : BottomNavItem(
        route = "home",
        label = "홈",
        icon = R.drawable.ic_menu_home
    )
    data object Shop : BottomNavItem(
        route = "shop",
        label = "구매하기",
        icon = R.drawable.ic_menu_shop
    )
    data object Wishlist : BottomNavItem(
        route = "wishlist",
        label = "위시리스트",
        icon = R.drawable.ic_menu_wishlist
    )
    data object Cart : BottomNavItem(
        route = "cart",
        label = "장바구니",
        icon = R.drawable.ic_menu_cart
    )
    data object Profile : BottomNavItem(
        route = "profile",
        label = "프로필",
        icon = R.drawable.ic_menu_profile
    )

    companion object {
        val items = listOf(Home, Shop, Wishlist, Cart, Profile)
    }
}