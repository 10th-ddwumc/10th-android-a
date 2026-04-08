package com.example.myapplication.ui.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ShopProduct
import com.example.myapplication.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val items: List<ShopProduct>,
    private val onWishlistChanged: (List<ShopProduct>) -> Unit,
    private val itemWidth: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    private val isWishlist: Boolean = false,
) : RecyclerView.Adapter<ShopProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemShopProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShopProduct) {
            binding.ivProduct.setImageResource(item.imageResId)
            binding.tvProductName.text = item.name
            binding.tvPrice.text = "₩${item.price}"
            binding.tvDescription.text = item.description
            binding.tvColor.text = "${item.color} colours"
            binding.tvTag.text = item.tag ?: ""

            binding.tvTag.visibility = if (isWishlist) View.GONE else View.VISIBLE

            binding.ivWishlist.setImageResource(
                if (item.isLiked) R.drawable.ic_liked else R.drawable.ic_unliked
            )

            binding.ivWishlist.setOnClickListener {
                item.isLiked = !item.isLiked
                binding.ivWishlist.setImageResource(
                    if (item.isLiked) R.drawable.ic_liked else R.drawable.ic_unliked
                )
                onWishlistChanged(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.root.layoutParams.width = itemWidth
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}