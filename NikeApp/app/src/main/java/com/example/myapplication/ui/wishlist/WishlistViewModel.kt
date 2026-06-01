package com.example.myapplication.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ShopProduct
import com.example.myapplication.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(private val productRepository: ProductRepository = ProductRepository()) : ViewModel() {
    private val _wishlist = MutableStateFlow<List<ShopProduct>>(emptyList())
    val wishlist: StateFlow<List<ShopProduct>> = _wishlist.asStateFlow()

    init {
        fetchWishlist()
    }

    private fun fetchWishlist() {
        viewModelScope.launch {
            productRepository.getWishlistProducts().collect {
                _wishlist.value = it
            }
        }
    }
}
