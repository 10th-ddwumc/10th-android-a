package com.example.umc_assignment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.umc_assignment2.databinding.ActivityProductDetailBinding
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("productName") ?: ""
        val price = intent.getStringExtra("productPrice") ?: ""

        binding.detailTitle.text = name
        binding.detailPrice.text = price

        binding.detailBackBtn.setOnClickListener {
            finish()
        }
    }
}