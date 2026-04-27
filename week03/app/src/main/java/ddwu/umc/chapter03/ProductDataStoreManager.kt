package ddwu.umc.chapter03

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

    suspend fun initHomeDummyDataIfNeeded() {
        val currentData = getHomeProducts().first()

        if (currentData.isEmpty()) {
            val initialDummyData = listOf(
                ProductData(null, R.drawable.jordan_xxxi, "Air Jordan XXXVI", "US$185", null, null, null),
                ProductData(null, R.drawable.airforce107, "Nike Air Force 1 '07", "US$115", null, null, null)
            )
            saveHomeProducts(initialDummyData)
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

    //데이터가 비어있을 때만 샘플을 채워 넣고, 이미 있으면 건드리지 않는 방어막 함수
    suspend fun initShopDummyDataIfNeeded() {
        val currentData = getShopProducts().first()

        if (currentData.isEmpty()) {
            val shopDummyData = listOf(
                ProductData(11, R.drawable.socks6pair, "Nike Everyday Plus Cushioned", "US$10", false, "Training Ankle Socks(6Pairs)", "5 Colours"),
                ProductData(12, R.drawable.socks_elite_crew, "Nike Elite Crew", "US$16", false, "Basketball Socks", "7 Colours"),
                ProductData(13, R.drawable.airforce107, "Nike Air Force 1'07", "US$115", true, "Women's Shoes", "5 Colours"),
                ProductData(14, R.drawable.airforce107ssentials, "Jordan ENike Air Force 1'07ssentials", "US$115", true, "Mens's Shoes", "2 Colours")
            )
            saveShopProducts(shopDummyData)
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