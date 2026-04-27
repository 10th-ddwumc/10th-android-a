package ddwu.umc.chapter03.shop

import android.os.Bundle
import android.util.Log
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
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.initShopDummyDataIfNeeded()
        }

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
                it.copy(isWished =  !it.isWished) //하트 색깔 반대로 바꿔서 통과!
            else
                it
        }

        // 화면(View)이 살아있는 동안만 안전하게 백그라운드 작업 실행 (앱 멈춤 방지)
        // 뷰 생명주기에 맞춘 안전한 코루틴(백그라운드) 실행
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.saveShopProducts(updatedList)

            val wishlist = updatedList.filter{it.isWished} //true인 것만 filter
            dataStoreManager.saveWishlistProducts(wishlist)
        }
    }


    private fun observeDataStore() {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getShopProducts().collect { productList ->


                Log.d("DataStoreCheck", "현재 샵 저장소 데이터: $productList")


                currentShopList = productList //최신 데이터로 업데이트!
                shopAdapter.updateData(productList) // 화면(어댑터) 새로고침!
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}