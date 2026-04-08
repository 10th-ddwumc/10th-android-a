package com.example.umc_assignment2

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "nike_store")

class DataStoreManager(private val context: Context) {
    private val gson = Gson()
    companion object {
        val HOME_PRODUCTS = stringPreferencesKey("home_products")
        val CART_PRODUCTS = stringPreferencesKey("cart_products")
        val WISHLIST_PRODUCTS = stringPreferencesKey("wishlist_products")
        val SHOP_PRODUCTS = stringPreferencesKey("shop_products")
    }

    suspend fun saveHomeProducts(productList: List<Product>) {
        val jsonString = gson.toJson(productList)
        context.dataStore.edit { prefs ->
            prefs[HOME_PRODUCTS] = jsonString
        }
    }

    suspend fun saveShopProducts(productList: List<Product>) {
        val jsonString = gson.toJson(productList)
        context.dataStore.edit { prefs ->
            prefs[SHOP_PRODUCTS] = jsonString
        }
    }

    fun getHomeProducts(): Flow<List<Product>> {
        return context.dataStore.data.map { prefs ->
            val jsonString = prefs[HOME_PRODUCTS] ?: "[]"

            val productArray = gson.fromJson(jsonString, Array<Product>::class.java)
            productArray.toList()
        }
    }

    fun getShopProducts(): Flow<List<Product>> {
        return context.dataStore.data.map { prefs ->
            val jsonString = prefs[SHOP_PRODUCTS] ?: "[]"
            val productArray = gson.fromJson(jsonString, Array<Product>::class.java)
            productArray.toList()
        }
    }

    fun getWishlist(): Flow<List<WishItem>> {
        return context.dataStore.data.map { prefs ->
            val jsonString = prefs[WISHLIST_PRODUCTS] ?: "[]"
            val itemArray = gson.fromJson(jsonString, Array<WishItem>::class.java)
            itemArray.toList()
        }
    }

    fun isWishlisted(productName: String): Flow<Boolean> {
        return getWishlist().map { list ->
            list.any { it.name == productName }
        }
    }
    suspend fun toggleWishlistItem(item: WishItem) {
        context.dataStore.edit { prefs ->
            val jsonString = prefs[WISHLIST_PRODUCTS] ?: "[]"
            val currentList = gson.fromJson(jsonString, Array<WishItem>::class.java).toMutableList()

            val existingItem = currentList.find { it.name == item.name }

            if (existingItem != null) {
                currentList.remove(existingItem)
            } else {
                currentList.add(item)
            }

            prefs[WISHLIST_PRODUCTS] = gson.toJson(currentList)
        }
    }
    suspend fun initializeShopDataIfEmpty() {
        val currentData = getShopProducts().first()

        if (currentData.isEmpty()) {
            val initialList = listOf(
                Product("Nike Everyday Plus Cushioned", "US$10", R.drawable.image_socks, "Tops"),
                Product("Nike Elite Crew", "US$16", R.drawable.ic_launcher_background, "Tops"),
                Product("Air Force 1 '07", "US$115", R.drawable.image_force, "Sale"),
                Product("Jordan ENike Air Force 1 '07ssentials", "US$115", R.drawable.image_mid, "Sale")
            )
            saveShopProducts(initialList)
        }
    }

    suspend fun initializeHomeDataIfEmpty() {
        val currentData = getHomeProducts().first()

        if (currentData.isEmpty()) {
            val initialList = listOf(
                Product("Air Jordan XXXVI", "US$185", R.drawable.image_jordan),
                Product("Nike Air Force 1 '07", "US$115", R.drawable.image_force)
            )
            saveHomeProducts(initialList)
        }
    }


}