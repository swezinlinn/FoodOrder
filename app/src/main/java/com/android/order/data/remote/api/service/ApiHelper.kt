package com.android.order.data.remote.api.service

import com.android.order.data.model.IngredientResponse
import com.android.order.data.model.OrderListResponse
import io.reactivex.Single

interface ApiHelper {

    fun getIngredientList(): Single<IngredientResponse>
    fun getOrderList(): Single<OrderListResponse>
}