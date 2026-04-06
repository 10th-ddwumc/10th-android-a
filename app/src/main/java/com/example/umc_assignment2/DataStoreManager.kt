package com.example.umc_assignment2

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "nike_store")

class DataStoreManager(private val context: Context) {
    private val gson = Gson()

    companion object {
        val HOME_PRODUCTS = stringPreferencesKey("home_products")
        val CART_PRODUCTS = stringPreferencesKey("cart_products")
        val WISHLIST_PRODUCTS = stringPreferencesKey("wishlist_products")
    }

    suspend fun saveHomeProducts(productList: List<Product>) {
        val jsonString = gson.toJson(productList)
        context.dataStore.edit { prefs ->
            prefs[HOME_PRODUCTS] = jsonString
        }
    }

    fun getHomeProducts(): Flow<List<Product>> {
        return context.dataStore.data.map { prefs ->
            val jsonString = prefs[HOME_PRODUCTS] ?: "[]"

            // Gson으로 String을 Array로 바꾼 뒤 List로 변환
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


}