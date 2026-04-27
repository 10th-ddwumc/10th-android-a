package com.example.myapplication.ui.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.WishlistProduct
import com.example.myapplication.databinding.ItemWishlistProductBinding

class WishlistProductAdapter(
    private val items: List<WishlistProduct>,
    private val itemWidth: Int = ViewGroup.LayoutParams.MATCH_PARENT
) : RecyclerView.Adapter<WishlistProductAdapter.WishlistViewHolder>() {

    inner class WishlistViewHolder(
        private val binding: ItemWishlistProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WishlistProduct) {
            binding.ivProduct.setImageResource(item.imageResId)
            binding.tvProductName.text = item.name
            binding.tvPrice.text = "₩${item.price}"
            binding.tvDescription.text = item.description
            binding.tvColor.text = "${item.color} colours"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.root.layoutParams.width = itemWidth
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}