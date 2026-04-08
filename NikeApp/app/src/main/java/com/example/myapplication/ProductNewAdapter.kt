package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemProductBinding
import com.example.myapplication.databinding.ItemProductNewBinding

class ProductNewAdapter(
    private val items: List<Product>,
    private val itemWidth: Int = ViewGroup.LayoutParams.MATCH_PARENT
) : RecyclerView.Adapter<ProductNewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemProductNewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.ivProduct.setImageResource(item.image)
            binding.tvProductName.text = item.name
            binding.tvPrice.text = "₩${item.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductNewBinding.inflate(
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