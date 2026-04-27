package com.example.myapplication.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreManager {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "product_store")
    private val HOME_PRODUCT_LIST_KEY = stringPreferencesKey("home_product_list")
    private val SHOP_PRODUCT_LIST_KEY = stringPreferencesKey("shop_product_list")
    private val WISHLIST_PRODUCT_LIST_KEY = stringPreferencesKey("wishlist_product_list_key")
    private val gson = Gson()
    private const val TAG = "WishlistDebug"

    // 초기 더미 데이터
    private val dummyHomeProducts = listOf(
        HomeProduct(R.drawable.product1, "나이키 보메로 프리미엄", 149000),
        HomeProduct(R.drawable.product2, "잉글랜드 2026 스타디움 홈", 135000),
        HomeProduct(R.drawable.product3, "조던 컴포트 에라", 149000),
    )

    private val dummyShopProducts = listOf(
        ShopProduct(1, "신제품", R.drawable.product1, "나이키 보메로 프리미엄", "여성 로드 러닝화", 3, 149000),
        ShopProduct(2, "신제품", R.drawable.product2, "잉글랜드 2026 스타디움 홈", "남성 나이키 드라이 핏 축구 레플리카 저지", 2, 135000),
        ShopProduct(3, "베스트셀러", R.drawable.product3, "조던 컴포트 에라", "남성 신발", 5, 149000),
    )

    // suspend로 비동기 처리
    suspend fun saveHomeProducts(context: Context, products: List<HomeProduct>) {
        val json = gson.toJson(products) // JSON 변환 (DataStore은 문자열만 저장 가능)
        context.datastore.edit { prefs ->
            prefs[HOME_PRODUCT_LIST_KEY] = json
        }
    }

    // Flow를 반환만 하기 때문에 비동기 처리 필요 없음
    fun getHomeProducts(context: Context): Flow<List<HomeProduct>> {
        return context.datastore.data
            .map { prefs ->
                val json = prefs[HOME_PRODUCT_LIST_KEY]
                if (json.isNullOrEmpty()) {
                    // 데이터가 없으면 초기 더미 데이터 반환
                    dummyHomeProducts
                } else {
                    val type = object : TypeToken<List<HomeProduct>>() {}.type
                    gson.fromJson(json, type) ?: dummyHomeProducts
                }
            }
    }

    suspend fun saveShopProducts(context: Context, products: List<ShopProduct>) {
        val json = gson.toJson(products)
        context.datastore.edit { prefs ->
            prefs[SHOP_PRODUCT_LIST_KEY] = json
        }
    }

    fun getShopProducts(context: Context): Flow<List<ShopProduct>> {
        return context.datastore.data
            .map { prefs ->
                val json = prefs[SHOP_PRODUCT_LIST_KEY]
                if (json.isNullOrEmpty()) {
                    dummyShopProducts
                } else {
                    val type = object : TypeToken<List<ShopProduct>>() {}.type
                    gson.fromJson(json, type) ?: dummyShopProducts
                }
            }
    }

    suspend fun toggleWishlist(context: Context, target: ShopProduct) {
        Log.d(TAG, "toggle start -> id=${target.id}, name=${target.name}")

        val currentList = getShopProducts(context).first() // 현재 데이터 가져오기

        val updatedList = currentList.map {
            if (it.id == target.id) {
                it.copy(isLiked = !it.isLiked)
            } else it
        }

        saveShopProducts(context, updatedList)
    }
}