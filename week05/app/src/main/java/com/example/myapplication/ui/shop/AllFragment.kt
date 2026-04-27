package com.example.myapplication.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.data.DataStoreManager
import com.example.myapplication.databinding.FragmentAllBinding
import kotlinx.coroutines.launch

class AllFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ShopProductAdapter

    companion object {
        private const val TAG = "WishlistDebug"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ShopProductAdapter(
            items = emptyList(),
            onWishlistClick = { clickedItem ->
                Log.d(TAG, "Fragment received -> id=${clickedItem.id}, name=${clickedItem.name}")
                viewLifecycleOwner.lifecycleScope.launch {
                    DataStoreManager.toggleWishlist(requireContext(), clickedItem)
                }
            }
        )

        binding.rvProducts.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            DataStoreManager.getShopProducts(requireContext())
                .collect { savedList ->
                    adapter.updateItems(savedList)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}