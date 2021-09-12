package com.android.order.domain.model

data class Ingredient (
    var category: String = "",
    var categoryId: String = "",
    var list: List<Data> = emptyList()
)

data class Data(
    var id: Int = 0,
    var name: String = "",
    var image: String = "",
    var availableQuantity: Int = 0
)