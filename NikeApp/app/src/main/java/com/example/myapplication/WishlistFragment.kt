// WishlistFragment.kt
package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)

        // 임시 데이터
        val productList = listOf(
            Product(R.drawable.product1, "나이키 보메로 프리미엄", 149000, "여성 로드 러닝화", "신제품"),
            Product(R.drawable.product2, "잉글랜드 2026 스타디움 홈", 135000, "남성 나이키 드라이 핏 축구 레플리카 저지", "신제품"),
            Product(R.drawable.product3, "조던 컴포트 에라", 149000, "남성 신발", "베스트셀러"),
        )

        binding.rvProducts.adapter = ProductAdapter(productList)
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }
}