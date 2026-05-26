package com.example.umc_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.umc_assignment2.ui.HomeScreen
import com.example.umc_assignment2.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val products by viewModel.homeProducts.collectAsState(initial = emptyList())

                HomeScreen(
                    products = products,
                    onItemClick = { product ->
                        val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                        intent.putExtra("productName", product.name)
                        intent.putExtra("productPrice", product.price)
                        intent.putExtra("productImage", product.imageRes)
                        startActivity(intent)
                    },
                    onWishClick = { product ->
                        val wishItem = WishItem(product.name, product.price, product.imageRes)
                        viewModel.toggleWish(wishItem)
                    }
                )
            }
        }
    }
}