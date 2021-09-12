package com.android.order.data.remote.api

import com.android.order.data.model.IngredientResponse
import com.android.order.data.model.OrderListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("ingredient_by_category")
    fun getIngredient() : Single<IngredientResponse>

    @GET("order_list")
    fun getOrderList() : Single<OrderListResponse>
}