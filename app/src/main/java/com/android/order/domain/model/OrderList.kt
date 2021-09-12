package com.android.order.domain.model

data class OrderList(var id: Int = 0,
var item: List<Item> = emptyList(),
var quantity: Int = 0,
var createdAt: String = "",
var alertedAt: String = "",
var expiredAt: String = "")

data class Item(
    var title: String? = "",
    var quantity: Int? = 0,
    var addon: List<Addon> = emptyList(),
)

data class Addon(
        var id: Int = 0,
        var title: String = "",
        var quantity: Int = 0
)