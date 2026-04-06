package com.example.myapplication.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.DataStoreManager
import com.example.myapplication.databinding.FragmentWishlistBinding
import com.example.myapplication.ui.shop.ShopProductAdapter
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            DataStoreManager.getShopProducts(requireContext())
                .collect { shopList ->  // 전체 리스트
                    val wishlist = shopList.filter { it.isLiked }
                    binding.rvProducts.adapter = ShopProductAdapter(
                        items = wishlist,
                        onWishlistChanged = { updatedWishlist ->
                            lifecycleScope.launch {
                                // 전체 리스트에서 isLiked 상태만 업데이트
                                val mergedList = shopList.map { shopItem ->
                                    val updated = updatedWishlist.find { it.name == shopItem.name }
                                    updated ?: shopItem
                                }
                                DataStoreManager.saveShopProducts(requireContext(), mergedList)
                            }
                        },
                        isWishlist = true,
                    )
                }
        }
    }
}