package com.example.umc_assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wishlist, container, false)
        val rvWishlist = view.findViewById<RecyclerView>(R.id.rv_wishlist)

        rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
        dataStoreManager = DataStoreManager(requireContext())

        lifecycleScope.launch {
            dataStoreManager.getWishlist().collect { wishList ->
                rvWishlist.adapter = WishlistAdapter(ArrayList(wishList))
            }
        }

        return view

//        val dummyData = arrayListOf(
//            WishItem("Air Jordan 1 Mid", "US$125", R.drawable.image_socks),
//            WishItem("Nike Everyday Plus", "US$10", R.drawable.image_mid)
//        )
//        val rvWishlist = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_wishlist)
//        val wishlistAdapter = WishlistAdapter(dummyData)
//
//        rvWishlist.adapter = WishlistAdapter(dummyData)
//
//        return view

    }
}