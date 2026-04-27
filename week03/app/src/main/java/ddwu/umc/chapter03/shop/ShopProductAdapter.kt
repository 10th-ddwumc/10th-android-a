package ddwu.umc.chapter03.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.databinding.ItemShopProductBinding

class ShopProductAdapter(private var productList: List<ProductData>,
                         private val onHeartClick: (ProductData) -> Unit) :
    RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding, onHeartClick)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newData: List<ProductData>) {
        this.productList = newData
        notifyDataSetChanged()
    }
}