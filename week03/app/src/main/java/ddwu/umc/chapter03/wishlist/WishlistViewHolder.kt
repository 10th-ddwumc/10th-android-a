package ddwu.umc.chapter03.wishlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.databinding.ItemWishlistProductBinding

class WishlistViewHolder(val binding: ItemWishlistProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: WishlistData) {

        binding.itemWishlistIv.setImageResource(product.image)

        binding.itemWishlistNameTv.text = product.name

        binding.itemWishlistCategoryTv.text = product.category
        binding.itemWishlistCategoryTv.visibility =
            if (product.category.isBlank()) View.GONE
            else View.VISIBLE

        binding.itemWishlistColorsTv.text = product.colors
        binding.itemWishlistColorsTv.visibility =
            if (product.colors.isBlank()) View.GONE
            else View.VISIBLE

        binding.itemWishlistPriceTv.text = product.price

    }
}