package com.example.umc_assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_assignment2.databinding.ItemProductBinding
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class WishlistAdapter(private val itemList: List<WishItem>) :
    RecyclerView.Adapter<WishlistAdapter.WishViewHolder>() {

    class WishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_item_name)
        val price: TextView = view.findViewById(R.id.tv_item_price)
        val img: ImageView = view.findViewById(R.id.iv_item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return WishViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.price.text = item.price
        holder.img.setImageResource(item.imgRes)
    }

    override fun getItemCount() = itemList.size
}