package ddwu.umc.chapter03.shop

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.databinding.ItemShopProductBinding

class ShopViewHolder(val binding: ItemShopProductBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ShopProductData) {
        binding.itemShopIv.setImageResource(product.image)

//        true일때
        binding.itemShopBestsellerTv.visibility =
            if (product.isBestSeller) View.VISIBLE
            else View.GONE

        binding.itemShopNameTv.text = product.name


        binding.itemShopCategoryTv.text = product.category
        binding.itemShopCategoryTv.visibility =
            if (product.category.isBlank()) View.GONE
            else View.VISIBLE

        binding.itemShopColorsTv.text = product.colors
        binding.itemShopColorsTv.visibility =
            if (product.colors.isBlank()) View.GONE
            else View.VISIBLE


        binding.itemShopPriceTv.text = product.price
    }

}