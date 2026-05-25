package ddwu.umc.chapter02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

// 1. 화면 이동을 위한
sealed interface Route {
    @Serializable data object Home : Route
    @Serializable data object Shop : Route
    @Serializable data object Wishlist : Route
    @Serializable data object Cart : Route
    @Serializable data object Profile : Route
}

// 2. 하단 탭에 들어갈 정보
data class BottomNavItem(
    val route: Route,
    val title: String,
    val iconResId: Int
)

val bottomNavItems = listOf(
    BottomNavItem(Route.Home, "홈", R.drawable.ic_home),
    BottomNavItem(Route.Shop, "구매하기", R.drawable.ic_search),
    BottomNavItem(Route.Wishlist, "위시리스트", R.drawable.ic_heart),
    BottomNavItem(Route.Cart, "장바구니", R.drawable.ic_cart),
    BottomNavItem(Route.Profile, "프로필", R.drawable.ic_profile)
)

// 3. 메인 액티비티
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

// 4. 앱의 전체 뼈대 (바텀바 + 네비게이션)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                bottomNavItems.forEach { item ->
                    val isSelected = currentDestination?.hasRoute(item.route::class) == true

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconResId),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text(item.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color.Black,
                            unselectedIconColor = Color(0xFF767676),
                            unselectedTextColor = Color(0xFF767676),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.Home> { HomeScreen() }
            composable<Route.Shop> { ShopScreen() }
            composable<Route.Wishlist> { WishlistScreen() }

            composable<Route.Cart> {
                CartScreen(
                    onNavigateToShop = {
                        navController.navigate(Route.Shop) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable<Route.Profile> { ProfileScreen() }
        }
    }
}