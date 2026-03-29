package ddwu.umc.chapter03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ddwu.umc.chapter03.databinding.ActivityMainBinding
import ddwu.umc.chapter03.home.HomeFragment
import ddwu.umc.chapter03.shop.ShopFragment
import ddwu.umc.chapter03.wishlist.WishlistFragment

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 처음 앱을 켰을 때 기본으로 보여줄 화면 (홈 화면) 세팅
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, HomeFragment())
            .commit()

        // 하단 탭 클릭 이벤트 처리
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //메인 화면
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, HomeFragment())
                        .commit()
                    true
                }

                //구매하기 화면
                R.id.shopFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ShopFragment())
                        .commit()
                    true
                }

                //위시리스트 화면
                R.id.wishlistFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, WishlistFragment())
                        .commit()
                    true
                }

                //장바구니 화면
                R.id.cartFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, CartFragment())
                        .commit()
                    true
                }

                //프로필 화면
                R.id.profileFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}