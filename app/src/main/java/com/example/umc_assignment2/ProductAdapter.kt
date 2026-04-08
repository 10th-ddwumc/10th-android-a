package com.example.umc_assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.RoundedCornerCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_assignment2.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: ArrayList<Product>,
    private val onItemClick: (Product) -> Unit,
    private val onWishClick: (Product, ImageView) -> Unit,
    private val checkWishStatus: (Product, ImageView) -> Unit,
    private val isHome: Boolean = false


) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    fun updateData(newData: List<Product>) {
        productList.clear()
        productList.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product:Product) {
            binding.itemTitle.text = product.name
            binding.itemPrice.text = product.price
            binding.itemImage.setImageResource(product.imageRes)

            checkWishStatus(product, binding.itemWishBtn)

            binding.root.setOnClickListener {
                onItemClick(product)
            }

            binding.itemWishBtn.setOnClickListener {
                onWishClick(product, binding.itemWishBtn)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        if (isHome) {
            val layoutParams = binding.root.layoutParams
            layoutParams.width = (parent.measuredWidth * 0.85).toInt()
            binding.root.layoutParams = layoutParams
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}