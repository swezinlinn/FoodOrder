package com.android.order.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class IngredientResponse {
    @SerializedName("ingredient")
    @Expose
    var ingredient: List<Ingredient>? = null

    class Ingredient {
        @SerializedName("category")
        @Expose
        var category: String? = null

        @SerializedName("categoryId")
        @Expose
        var categoryId: String? = null

        @SerializedName("list")
        @Expose
        var list: List<Data>? = null
    }

    class Data {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("availableQuantity")
        @Expose
        var availableQuantity: Int? = null
    }
}