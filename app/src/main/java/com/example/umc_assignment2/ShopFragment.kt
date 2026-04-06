package com.example.umc_assignment2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())

        val recyclerView = view.findViewById<RecyclerView>(R.id.shop_product_rv)

        val itemList = arrayListOf(
            Product("Nike Everyday Plus Cushioned", "US$10", R.drawable.image_socks),
            Product("Nike Elite Crew", "US$16", R.drawable.image_socks),
            Product("Air Force 1 '07", "US$115", R.drawable.image_mid),
            Product("Jordan ENike Air Force 1 '07ssentials", "US$115", R.drawable.image_mid)
        )

        val shopAdapter = ProductAdapter(
            productList = itemList,
            isHome = false,
            onItemClick = { product ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("productName", product.name)
                intent.putExtra("productPrice", product.price)
                intent.putExtra("productImage", product.imageRes)
                startActivity(intent)
            },

            onWishClick = { product, heartImageView ->
                lifecycleScope.launch {
                    val wishItem = WishItem(product.name, product.price, product.imageRes)
                    dataStoreManager.toggleWishlistItem(wishItem)

                    val isWished = dataStoreManager.isWishlisted(product.name).first()
                    if (isWished) {
                        heartImageView.setImageResource(R.drawable.icon_redheart)
                    } else {
                        heartImageView.setImageResource(R.drawable.icon_emptyheart)
                    }
                }
            },

            checkWishStatus = { product, heartImageView ->
                lifecycleScope.launch {
                    val isWished = dataStoreManager.isWishlisted(product.name).first()

                    if (isWished) {
                        heartImageView.setImageResource(R.drawable.icon_redheart)
                    } else {
                        heartImageView.setImageResource(R.drawable.icon_emptyheart)
                    }
                }
            }
        )

        recyclerView.adapter = shopAdapter

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
    }
}