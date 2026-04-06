package com.example.myapplication.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.DataStoreManager
import com.example.myapplication.data.ShopProduct
import com.example.myapplication.databinding.FragmentAllBinding
import kotlinx.coroutines.launch

class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            DataStoreManager.getShopProducts(requireContext())
                .collect { savedList ->
                    if (savedList.isEmpty()) {
                        val dummyList = listOf(
                            ShopProduct("신제품", R.drawable.product1, "나이키 보메로 프리미엄", "여성 로드 러닝화", 3, 149000),
                            ShopProduct("신제품", R.drawable.product2, "잉글랜드 2026 스타디움 홈", "남성 나이키 드라이 핏 축구 레플리카 저지", 2, 135000),
                            ShopProduct("베스트셀러", R.drawable.product3, "조던 컴포트 에라", "남성 신발", 5, 149000),
                        )
                        DataStoreManager.saveShopProducts(requireContext(), dummyList)
                    } else {
                        binding.rvProducts.adapter = ShopProductAdapter(
                            items = savedList,
                            onWishlistChanged = { updatedList ->
                                lifecycleScope.launch{
                                    DataStoreManager.saveShopProducts(requireContext(), updatedList)
                                }
                            }
                        )
                    }
                }
        }
    }
}