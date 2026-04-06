package ddwu.umc.chapter03.wishlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.databinding.ItemWishlistProductBinding

class WishlistViewHolder(val binding: ItemWishlistProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {

        binding.itemWishlistIv.setImageResource(product.image)
        binding.itemWishlistNameTv.text = product.name
        binding.itemWishlistPriceTv.text = product.price


    }
}