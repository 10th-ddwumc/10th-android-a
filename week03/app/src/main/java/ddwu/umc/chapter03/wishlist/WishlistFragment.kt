package ddwu.umc.chapter03.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.umc.chapter03.R
import ddwu.umc.chapter03.databinding.FragmentWishlistBinding


class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishlistDummyData = arrayListOf(
            WishlistData(
                R.drawable.jordan1mid,
                "Air Jordan 1 Mid",
                "",
                "",
                "US$125"
            ),
            WishlistData(
                R.drawable.socks6pair,
                "Nike Everyday Plus Cushioned",
                "Training Ankle Socks(6Pairs)",
                "5 Colours",
                "US$10"
            )
        )

        val wishListAdapter = WishlistAdapter(wishlistDummyData)
        binding.wishlistRecyclerview.adapter = wishListAdapter

        binding.wishlistRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}