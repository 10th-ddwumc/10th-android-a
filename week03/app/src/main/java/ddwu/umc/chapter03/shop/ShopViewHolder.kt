package ddwu.umc.chapter03.shop

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.R
import ddwu.umc.chapter03.databinding.ItemShopProductBinding

class ShopViewHolder(
    val binding: ItemShopProductBinding,
    private val onHeartClick: (ProductData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        binding.itemShopIv.setImageResource(product.image)

        binding.itemShopBestsellerTv.visibility =
            if (product.isBestSeller == true) View.VISIBLE else View.GONE

        binding.itemShopNameTv.text = product.name

        binding.itemShopCategoryTv.text = product.category ?: ""
        binding.itemShopCategoryTv.visibility =
            if (product.category.isNullOrBlank()) View.GONE else View.VISIBLE

        binding.itemShopColorsTv.text = product.colors ?: ""
        binding.itemShopColorsTv.visibility =
            if (product.colors.isNullOrBlank()) View.GONE else View.VISIBLE

        binding.itemShopPriceTv.text = product.price


        if (product.isWished) {
            binding.itemShopHeartIv.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding.itemShopHeartIv.setImageResource(R.drawable.ic_heart)
        }

        // 하트 버튼 클릭 이벤트
        binding.itemShopHeartIv.setOnClickListener {
            onHeartClick(product)
        }
    }
}