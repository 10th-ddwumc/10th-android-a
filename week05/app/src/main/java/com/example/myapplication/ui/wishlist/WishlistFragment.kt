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

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ShopProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ShopProductAdapter(
            items = emptyList(),
            onWishlistClick = { clickedItem ->
                viewLifecycleOwner.lifecycleScope.launch {
                    DataStoreManager.toggleWishlist(requireContext(), clickedItem)
                }
            },
            isWishlist = true
        )

        binding.rvProducts.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            DataStoreManager.getShopProducts(requireContext())
                .collect { shopList ->
                    val wishlist = shopList.filter { it.isLiked }
                    adapter.updateItems(wishlist)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}