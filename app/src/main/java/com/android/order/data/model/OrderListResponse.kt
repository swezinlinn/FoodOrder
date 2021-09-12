package com.android.order.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class OrderListResponse {
    @SerializedName("status")
    @Expose
    val status: Status? = null

    @SerializedName("data")
    @Expose
    val data: List<Data?>? = null

    class Data {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("item")
        @Expose
        var item: List<Item>? = null

        @SerializedName("quantity")
        @Expose
        var quantity: Int? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("alerted_at")
        @Expose
        var alertedAt: String? = null

        @SerializedName("expired_at")
        @Expose
        var expiredAt: String? = null

        class Item {
            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("quantity")
            @Expose
            var quantity: Int? = null

            @SerializedName("addon")
            @Expose
            var addon: List<Addon>? = null

            class Addon {
                @SerializedName("id")
                @Expose
                var id: Int? = null

                @SerializedName("title")
                @Expose
                var title: String? = null

                @SerializedName("quantity")
                @Expose
                var quantity: Int? = null
            }
        }
    }
    class Status {
        @SerializedName("success")
        @Expose
        var success: Boolean? = null

        @SerializedName("statusCode")
        @Expose
        var statusCode: Int? = null

        @SerializedName("message")
        @Expose
        var message: String? = null
    }
}