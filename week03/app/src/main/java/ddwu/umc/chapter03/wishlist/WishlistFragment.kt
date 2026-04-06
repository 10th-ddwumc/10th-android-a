package ddwu.umc.chapter03.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.umc.chapter03.ProductDataStoreManager
import ddwu.umc.chapter03.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch


class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding

    private lateinit var dataStoreManager: ProductDataStoreManager
    private lateinit var wishlistAdapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = ProductDataStoreManager(requireContext())
        wishlistAdapter = WishlistAdapter(emptyList())

        binding.wishlistRecyclerview.adapter = wishlistAdapter
        binding.wishlistRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)

        observeWishlist()

    }

    private fun observeWishlist() {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getWishlistProducts().collect { wishlist ->
                wishlistAdapter.updateData(wishlist)
            }
        }
    }

}