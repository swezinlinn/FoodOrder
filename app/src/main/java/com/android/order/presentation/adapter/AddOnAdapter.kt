package com.android.order.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.order.R
import com.android.order.domain.model.Addon
import java.util.ArrayList

class AddOnAdapter() : RecyclerView.Adapter<AddOnAdapter.ViewHolder>() {

    private var list: List<Addon> = ArrayList()

    constructor(order: List<Addon>) : this() {
        this.list = order
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_add_on, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addOnName.text = list[position].title
        holder.addOnQuantity.text = list[position].quantity.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val addOnName: TextView = itemView.findViewById(R.id.addOnName)
        val addOnQuantity: TextView = itemView.findViewById(R.id.addOnQuantity)
    }
}