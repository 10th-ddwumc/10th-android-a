package com.example.umc_assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_assignment2.databinding.ItemProductBinding

class ProductAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemTitle.text = productList[position].name
        holder.binding.itemPrice.text = productList[position].price
        // 이 코드가 추가되어야 사진이 보입니다!
        // itemImage는 ItemProductBinding(XML)에 정의된 ImageView의 ID입니다.
        // 본인의 XML ID가 다를 경우 그 이름으로 바꿔주세요.
//        holder.binding.itemImage.setImageResource(product.imageRes)
    }

    override fun getItemCount(): Int = productList.size
}