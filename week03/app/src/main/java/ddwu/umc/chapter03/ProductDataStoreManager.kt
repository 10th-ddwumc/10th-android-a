package ddwu.umc.chapter03

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 파일 최상단에 DataStore 인스턴스 생성
private val Context.dataStore by preferencesDataStore(name="product_prefs")

class ProductDataStoreManager(private val context: Context) {

    private val gson = Gson()

    companion object {
        val HOME_PRODUCTS_KEY = stringPreferencesKey("home_products")
        val SHOP_PRODUCTS_KEY= stringPreferencesKey("shop_products")
        val WISHLIST_PRODUCTS_KEY = stringPreferencesKey("wishlist_products")
    }

    // 1. 홈 화면
    suspend fun saveHomeProducts(productList: List<ProductData>) {
        context.dataStore.edit { preferences ->
            preferences[HOME_PRODUCTS_KEY] = gson.toJson(productList)
        }
    }
    fun getHomeProducts(): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[HOME_PRODUCTS_KEY] ?: "[]"
            val type = object: TypeToken<List<ProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }

    // 2. 샵 화면
    suspend fun saveShopProducts(productList: List<ProductData>) {
        context.dataStore.edit { preferences ->
            preferences[SHOP_PRODUCTS_KEY] = gson.toJson(productList)
        }
    }
    fun getShopProducts() : Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[SHOP_PRODUCTS_KEY] ?: "[]"
            val type = object: TypeToken<List<ProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }

    // 3. 위시리스트 화면
    suspend fun saveWishlistProducts(productList: List<ProductData>) {
        context.dataStore.edit { preferences ->
            preferences[WISHLIST_PRODUCTS_KEY] = gson.toJson(productList)
        }
    }
    fun getWishlistProducts(): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[WISHLIST_PRODUCTS_KEY] ?: "[]"
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}