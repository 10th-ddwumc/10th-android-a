package com.example.umc_assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. RecyclerView 찾기
        val recyclerView = view.findViewById<RecyclerView>(R.id.shop_product_rv)

        // 2. 가상의 데이터 리스트 생성 (본인의 데이터 클래스에 맞게 수정하세요)
        val itemList = arrayListOf(
            Product("Nike Everyday Plus", "US$10", R.drawable.shoes),
            Product("Nike Elite Crew", "US$16", R.drawable.shoes),
            Product("Air Force 1 '07", "US$115", R.drawable.shoes),
            Product("Jordan Nike Air", "US$115", R.drawable.shoes)
        )

        // 3. Adapter 연결
        val shopAdapter = ProductAdapter(itemList) // 본인이 만든 어댑터 클래스명
        recyclerView.adapter = shopAdapter

        // 4. GridLayoutManager 설정 (2열 격자)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
    }
}
