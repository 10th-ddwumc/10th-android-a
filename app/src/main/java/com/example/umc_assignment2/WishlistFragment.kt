package com.example.umc_assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WishlistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. 레이아웃을 인플레이트하여 view 변수에 담습니다.
        val view = inflater.inflate(R.layout.fragment_wishlist, container, false)

        // 2. 더미 데이터 생성
        val dummyData = arrayListOf(
            WishItem("Air Jordan 1 Mid", "US$125", R.drawable.shoes),
            WishItem("Nike Everyday Plus", "US$10", R.drawable.shoes),
            WishItem("Air Jordan 1 Mid", "US$125", R.drawable.shoes),
            WishItem("Nike Everyday Plus", "US$10", R.drawable.shoes)
        )

        // 3. 리사이클러뷰 찾기 및 어댑터 연결
        val rvWishlist = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_wishlist)
        val wishlistAdapter = WishlistAdapter(dummyData)

        rvWishlist.adapter = WishlistAdapter(dummyData)

        return view

    }
}