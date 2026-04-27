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

    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var homeAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())

        setupRecyclerView()
        setupInitialDataIfEmpty()
        observeDataStore()
    }

    private fun setupRecyclerView() {
        homeAdapter = ProductAdapter(
            productList = arrayListOf(),
            isHome = true,
            onItemClick = { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("productName", product.name)
                intent.putExtra("productPrice", product.price)
                intent.putExtra("productImage", product.imageRes)
                startActivity(intent)
            },
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
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupInitialDataIfEmpty() {
        lifecycleScope.launch {
            dataStoreManager.initializeHomeDataIfEmpty()
        }
    }

    private fun observeDataStore() {
        lifecycleScope.launch {
            dataStoreManager.getHomeProducts().collect { productList ->
                homeAdapter.updateData(productList)  // 데이터만 갱신!
            }
        }
    }

    override fun onDestroyView() {  // onDestroy → onDestroyView로 수정!
        super.onDestroyView()
        _binding = null
    }
}
