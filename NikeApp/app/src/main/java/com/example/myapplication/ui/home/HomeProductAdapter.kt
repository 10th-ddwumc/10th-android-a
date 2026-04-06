package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.HomeProduct
import com.example.myapplication.databinding.ItemHomeProductBinding

class HomeProductAdapter(
    private var items: List<HomeProduct>,
    private val itemWidth: Int = ViewGroup.LayoutParams.MATCH_PARENT
) : RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemHomeProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeProduct) { // ShopProduct -> HomeProduct
            binding.ivProduct.setImageResource(item.imageResId)
            binding.tvProductName.text = item.name
            binding.tvPrice.text = "₩${item.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemHomeProductBinding.inflate(
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