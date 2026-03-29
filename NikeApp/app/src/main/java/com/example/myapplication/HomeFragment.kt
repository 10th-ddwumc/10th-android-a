package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 임시 데이터
        val productList = listOf(
            Product(R.drawable.product1, "나이키 보메로 프리미엄", 149000),
            Product(R.drawable.product2, "잉글랜드 2026 스타디움 홈", 135000),
            Product(R.drawable.product3, "조던 컴포트 에라", 149000),
        )

        val itemWidth = (resources.displayMetrics.widthPixels * 0.75).toInt()
        binding.rvProducts.adapter = ProductNewAdapter(productList, itemWidth)
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
}