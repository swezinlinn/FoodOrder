package com.android.order.presentation.adapter

import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter
import com.android.order.R

open class SearchCustomAdapter(
    context: Context,
    cursor: Cursor?,
    flags: Int = 0
) : CursorAdapter(context, cursor, flags) {

    var suggestionIcon = R.drawable.ic_action_search


    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.layout_search_item, parent, false)
    }


    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val name = cursor.getString(cursor.getColumnIndex(
            "name"))

        val nameTv: TextView = view.findViewById(R.id.tv_str)
        nameTv.text = name
        val icon: ImageView = view.findViewById(R.id.iv_icon)
        icon.setImageResource(suggestionIcon)
    }
}