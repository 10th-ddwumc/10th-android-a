package ddwu.umc.chapter03.home

import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.databinding.ItemHomeProductBinding

class HomeViewHolder(val binding: ItemHomeProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductData) {
        binding.itemHomeProductIv.setImageResource(product.image)
        binding.itemHomeProductNameTv.text = product.name
        binding.itemHomeProductPriceTv.text = product.price
    }
}