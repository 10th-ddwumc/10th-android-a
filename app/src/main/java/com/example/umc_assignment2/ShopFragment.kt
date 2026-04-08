package com.example.umc_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_assignment2.databinding.FragmentShopBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var shopAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())

        if (binding.tabLayout.tabCount == 0) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("전체"))
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tops & T-shirts"))
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sale"))
        }

        setupRecyclerView()
        setupInitialDataIfEmpty()
        observeDataStore()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // 전체 탭
                        binding.shopProductRv.visibility = View.VISIBLE
                        binding.shopFragmentContainer.visibility = View.GONE
                    }
                    1 -> { // Tops & T-shirts 탭
                        binding.shopProductRv.visibility = View.GONE
                        binding.shopFragmentContainer.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, TopsFragment())
                            .commit()
                    }
                    2 -> { // Sale 탭
                        binding.shopProductRv.visibility = View.GONE
                        binding.shopFragmentContainer.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, SaleFragment())
                            .commit()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    private fun setupInitialDataIfEmpty() {
        lifecycleScope.launch {
            dataStoreManager.initializeShopDataIfEmpty()
        }
    }
    private fun setupRecyclerView() {
        shopAdapter = ProductAdapter(
            productList = arrayListOf(),
            isHome = false,
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

        binding.shopProductRv.adapter = shopAdapter
        binding.shopProductRv.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeDataStore() {
        lifecycleScope.launch {
            dataStoreManager.getShopProducts().collect { productList ->
                shopAdapter.updateData(productList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}