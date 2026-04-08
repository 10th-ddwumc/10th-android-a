package com.example.myapplication.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "product_store")
    private val HOME_PRODUCT_LIST_KEY = stringPreferencesKey("home_product_list")
    private val SHOP_PRODUCT_LIST_KEY = stringPreferencesKey("shop_product_list")
    private val WISHLIST_PRODUCT_LIST_KEY = stringPreferencesKey("wishlist_product_list_key")
    private val gson = Gson()

    // suspend로 비동기 처리
    suspend fun saveHomeProducts(context: Context, products: List<HomeProduct>) {
        val json = gson.toJson(products) // JSON 변환 (DataStore은 문자열만 저장 가능)
        context.datastore.edit { prefs ->
            prefs[HOME_PRODUCT_LIST_KEY] = json
        }
    }

    suspend fun saveShopProducts(context: Context, products: List<ShopProduct>) {
        val json = gson.toJson(products)
        context.datastore.edit { prefs ->
            prefs[SHOP_PRODUCT_LIST_KEY] = json
        }
    }

    suspend fun saveWishlistProducts(context: Context, products: List<WishlistProduct>) {
        val json = gson.toJson(products)
        context.datastore.edit { prefs ->
            prefs[WISHLIST_PRODUCT_LIST_KEY] = json
        }
    }

    // Flow를 반환만 하기 때문에 비동기 처리 필요 없음
    fun getHomeProducts(context: Context): Flow<List<HomeProduct>> {
        return context.datastore.data
            .map { prefs ->
                val json = prefs[HOME_PRODUCT_LIST_KEY] ?: return@map emptyList()
                val type = object : TypeToken<List<HomeProduct>>() {}.type // TypeToken으로 타입 지정
                gson.fromJson(json, type) ?: emptyList()
            }
    }

    fun getShopProducts(context: Context): Flow<List<ShopProduct>> {
        return context.datastore.data
            .map { prefs ->
                val json = prefs[SHOP_PRODUCT_LIST_KEY] ?: return@map emptyList()
                val type = object : TypeToken<List<ShopProduct>>() {}.type
                gson.fromJson(json, type) ?: emptyList()
            }
    }

    fun getWishlistProducts(context: Context): Flow<List<WishlistProduct>> {
        return context.datastore.data
            .map { prefs ->
                val json = prefs[WISHLIST_PRODUCT_LIST_KEY] ?: return@map emptyList()
                val type = object : TypeToken<List<WishlistProduct>>() {}.type
                gson.fromJson(json, type) ?: emptyList()
            }
    }
}