package com.example.umc_assignment2.repository

import com.example.umc_assignment2.DataStoreManager
import com.example.umc_assignment2.WishItem
import javax.inject.Inject

class UserLocalRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    fun getHomeProducts() = dataStoreManager.getHomeProducts()
    suspend fun initializeHomeData() = dataStoreManager.initializeHomeDataIfEmpty()

    fun getShopProducts() = dataStoreManager.getShopProducts()
    suspend fun initializeShopData() = dataStoreManager.initializeShopDataIfEmpty()

    suspend fun toggleWishItem(item: WishItem) = dataStoreManager.toggleWishlistItem(item)
    fun isWishlisted(name: String) = dataStoreManager.isWishlisted(name)
    suspend fun saveName(name: String) = dataStoreManager.saveName(name)
    fun getName() = dataStoreManager.getName()
}