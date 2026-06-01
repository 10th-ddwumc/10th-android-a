package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.HomeProduct
import com.example.myapplication.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepository = ProductRepository()) : ViewModel() {
    private val _products = MutableStateFlow<List<HomeProduct>>(emptyList())
    val products: StateFlow<List<HomeProduct>> = _products.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productRepository.getHomeProducts().collect {
                _products.value = it
            }
        }
    }
}
