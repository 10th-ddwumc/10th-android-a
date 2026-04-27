package ddwu.umc.chapter03.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.databinding.ItemHomeProductBinding

class HomeProductAdapter(private var productList: List<ProductData>):
    RecyclerView.Adapter<HomeViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newData: List<ProductData>) {
        this.productList = newData
        notifyDataSetChanged()
    }
}