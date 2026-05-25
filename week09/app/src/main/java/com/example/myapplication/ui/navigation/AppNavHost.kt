package com.example.myapplication.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screen.CartScreen
import com.example.myapplication.ui.screen.HomeScreen
import com.example.myapplication.ui.screen.ProfileScreen
import com.example.myapplication.ui.screen.ShopScreen
import com.example.myapplication.ui.screen.WishlistScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(BottomNavItem.Home.route)     { HomeScreen() }
        composable(BottomNavItem.Shop.route)     { ShopScreen() }
        composable(BottomNavItem.Wishlist.route) { WishlistScreen() }
        composable(BottomNavItem.Cart.route) {
            CartScreen(
                onOrderClick = {
                    navController.navigate(BottomNavItem.Shop.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        composable(BottomNavItem.Profile.route)  { ProfileScreen() }
    }
}