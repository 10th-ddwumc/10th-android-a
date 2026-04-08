package ddwu.umc.chapter03.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.shop.ShopProductData
import ddwu.umc.chapter03.shop.ShopViewHolder
import ddwu.umc.chapter03.databinding.ItemShopProductBinding

class ShopProductAdapter(private val productList: ArrayList<ShopProductData>) :
    RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}