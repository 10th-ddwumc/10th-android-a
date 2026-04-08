package com.example.umc_assignment2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umc_assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, HomeFragment())
            .commit()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, HomeFragment())
                        .commit()
                    true
                }

                R.id.shop -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ShopFragment())
                        .commit()
                    true
                }

                R.id.wishlist -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, WishlistFragment())
                        .commit()
                    true
                }

                R.id.cart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, CartFragment())
                        .commit()
                    true
                }

                R.id.profile -> {
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
