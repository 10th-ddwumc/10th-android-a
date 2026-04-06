package ddwu.umc.chapter03.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.ProductDataStoreManager
import ddwu.umc.chapter03.R
import ddwu.umc.chapter03.shop.ShopProductAdapter
import ddwu.umc.chapter03.databinding.FragmentShopBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!


    private lateinit var dataStoreManager: ProductDataStoreManager
    private lateinit var shopAdapter: ShopProductAdapter
    private var currentShopList: List<ProductData> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = ProductDataStoreManager(requireContext())
        shopAdapter = ShopProductAdapter(emptyList()) { clickedProduct ->
            toggleHeart(clickedProduct)
        }

        binding.shopRecyclerview.adapter = shopAdapter
        binding.shopRecyclerview.layoutManager = GridLayoutManager(context, 2)

        observeDataStore()
        setupInitialDataIfEmpty()

        binding.shopTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { //전체
                        binding.shopRecyclerview.visibility = View.VISIBLE
                        binding.shopFragmentContainer.visibility = View.GONE
                    }
                    1 -> { //Tops & T-Shirts
                        binding.shopRecyclerview.visibility = View.GONE
                        binding.shopFragmentContainer.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, ShopTopFragment())
                            .commit()
                    }
                    2 -> { //shoes
                        binding.shopRecyclerview.visibility = View.GONE
                        binding.shopFragmentContainer.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.shop_fragment_container, ShopShoesFragment())
                            .commit()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }
    private fun toggleHeart(clickedProduct: ProductData) {
        val updatedList = currentShopList.map {
            if (it.id == clickedProduct.id)
                it.copy(isWished =  !it.isWished)
            else
                it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.saveShopProducts(updatedList)

            val wishlist = updatedList.filter{it.isWished}
            dataStoreManager.saveWishlistProducts(wishlist)
        }
    }

    private fun setupInitialDataIfEmpty() {
        viewLifecycleOwner.lifecycleScope.launch {
            //현재 저장된 데이터를 한 번 가져와봄
            val currentData = dataStoreManager.getShopProducts().first()

            //비어있을 때만 더미 데이터를 넣음
            if (currentData.isEmpty()) {
                val shopDummyData = listOf(
                    ProductData(11, R.drawable.socks6pair, "Nike Everyday Plus Cushioned", "US$10", false, "Training Ankle Socks(6Pairs)", "5 Colours"),
                    ProductData(12, R.drawable.socks_elite_crew, "Nike Elite Crew", "US$16", false, "Basketball Socks", "7 Colours"),
                    ProductData(13, R.drawable.airforce107, "Nike Air Force 1'07", "US$115", true, "Women's Shoes", "5 Colours"),
                    ProductData(14, R.drawable.airforce107ssentials, "Jordan ENike Air Force 1'07ssentials", "US$115", true, "Mens's Shoes", "2 Colours")
                )
                dataStoreManager.saveShopProducts(shopDummyData)
            }
        }
    }

    private fun observeDataStore() {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getShopProducts().collect { productList ->
                currentShopList = productList // 현재 리스트 업데이트
                shopAdapter.updateData(productList) // 화면 새로고침
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}