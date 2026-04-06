package com.example.umc_assignment2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_assignment2.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())

        // DataStore에 저장
        lifecycleScope.launch {
            val initialData = listOf(
                Product("Air Jordan XXXVI", "US$185", R.drawable.image_jordan),
                Product("Nike Air Force 1 '07", "US$115", R.drawable.image_force)
            )
            dataStoreManager.saveHomeProducts(initialData)
        }

        lifecycleScope.launch {
            dataStoreManager.getHomeProducts().collect { productList ->
                val productAdapter = ProductAdapter(
                    productList = ArrayList(productList),
                    isHome = true,

                    // 상품 클릭
                    onItemClick = { product ->
                        val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                        intent.putExtra("productName", product.name)
                        intent.putExtra("productPrice", product.price)
                        intent.putExtra("productImage", product.imageRes)
                        startActivity(intent)
                    },

                    // 하트 클릭 (토글)
                    onWishClick = { product, heartImageView ->
                        lifecycleScope.launch {
                            val wishItem = WishItem(product.name, product.price, product.imageRes)
                            dataStoreManager.toggleWishlistItem(wishItem)

                            val isWished = dataStoreManager.isWishlisted(product.name).first()
                            if (isWished) {
                                heartImageView.setImageResource(R.drawable.icon_redheart)
                            } else {
                                heartImageView.setImageResource(R.drawable.icon_emptyheart)
                            }
                        }
                    },
                    //화면에 처음 그려질 때 상태 확인해서 색상 칠하기
                    checkWishStatus = { product, heartImageView ->
                        lifecycleScope.launch {
                            val isWished = dataStoreManager.isWishlisted(product.name).first()
                            if (isWished) {
                                heartImageView.setImageResource(R.drawable.icon_redheart)
                            } else {
                                heartImageView.setImageResource(R.drawable.icon_emptyheart)
                            }
                        }
                    }
                )

                binding.homeProductRv.apply {
                    adapter = productAdapter
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}