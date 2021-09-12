package com.android.order.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.order.R
import com.android.order.domain.model.Item
import java.util.ArrayList

class ItemAdapter(): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var list: List<Item> = ArrayList()
    private lateinit var context: Context

    constructor(context: Context, order: List<Item>) : this() {
        this.context = context
        this.list = order
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = list[position].title
        holder.itemQuantity.text = context.getString(R.string.detail_item_quantity,list[position].quantity.toString())
        val mLayoutManager = LinearLayoutManager(context)
        holder.rlAddOn.layoutManager = mLayoutManager
        holder.rlAddOn.setHasFixedSize(true)
        val adapter = AddOnAdapter(list[position].addon)
        holder.rlAddOn.adapter = adapter

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemQuantity: TextView = itemView.findViewById(R.id.quantity)
        val rlAddOn: RecyclerView = itemView.findViewById(R.id.addOn)
    }
}