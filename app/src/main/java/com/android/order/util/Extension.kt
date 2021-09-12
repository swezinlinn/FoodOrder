package com.android.order.util

import android.view.View
import com.android.order.domain.model.OrderList
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


fun getDate(time: String): Date{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    val date: Date = inputFormat.parse(time)
    return date
}

fun getDateString(time: Date) : String{
    val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.ENGLISH)
    return outputFormat.format(time)
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun getExpiredAt(order: OrderList) : Long{
    return getDate(order.expiredAt).time
}

fun getAlertAt(order: OrderList) : Long{
    return getDate(order.alertedAt).time
}

fun getCreatedAt(order: OrderList) : Long{
    return getDate(order.createdAt).time
}

inline fun <reified T> String.toObject(): T? {
    return try {
        val gson = Gson()
        gson.fromJson(this, T::class.java)
    } catch (e: Exception) {
        null
    }
}

fun Any.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)

}