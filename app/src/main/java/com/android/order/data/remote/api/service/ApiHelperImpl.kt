package com.android.order.data.remote.api.service

import com.android.order.data.model.IngredientResponse
import com.android.order.data.remote.api.Api
import com.android.order.data.model.OrderListResponse
import io.reactivex.Single

class ApiHelperImpl(
    private val api: Api
) : ApiHelper {

    override fun getIngredientList(): Single<IngredientResponse> {
        return api.getIngredient()
    }

    override fun getOrderList(): Single<OrderListResponse> {
        return api.getOrderList()
    }
}