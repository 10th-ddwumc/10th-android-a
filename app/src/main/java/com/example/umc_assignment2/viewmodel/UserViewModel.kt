package com.example.umc_assignment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umc_assignment2.Product // 본인 패키지 위치 확인!
import com.example.umc_assignment2.WishItem
import com.example.umc_assignment2.repository.UserData
import com.example.umc_assignment2.repository.UserLocalRepository
import com.example.umc_assignment2.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val remoteRepository: UserRepository,
    private val localRepository: UserLocalRepository
) : ViewModel() {

    private val _userList = MutableStateFlow<List<UserData>>(emptyList())
    val userList: StateFlow<List<UserData>> = _userList.asStateFlow()

    private val _homeProducts = MutableStateFlow<List<Product>>(emptyList())
    val homeProducts: StateFlow<List<Product>> = _homeProducts.asStateFlow()

    private val _storedName = MutableStateFlow("")
    val storedName: StateFlow<String> = _storedName.asStateFlow()

    init {
        observeStoredName()
        fetchHomeProducts()
    }

    fun fetchUserList(page: Int = 1) {
        viewModelScope.launch {
            val response = remoteRepository.getUserList(page)
            if (response.isSuccessful) {
                _userList.value = response.body()?.data ?: emptyList()
            }
        }
    }

    private fun fetchHomeProducts() {
        viewModelScope.launch {
            localRepository.initializeHomeData()
            localRepository.getHomeProducts().collectLatest { products ->
                _homeProducts.value = products
            }
        }
    }

    fun toggleWish(item: WishItem) {
        viewModelScope.launch {
            localRepository.toggleWishItem(item)
        }
    }

    fun saveName(name: String) {
        viewModelScope.launch {
            localRepository.saveName(name)
        }
    }

    private fun observeStoredName() {
        viewModelScope.launch {
            localRepository.getName().collectLatest { name ->
                _storedName.value = name
            }
        }
    }
}