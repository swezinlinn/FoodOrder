package com.android.order.data.repository

import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient
import com.android.order.domain.model.OrderList
import io.reactivex.Single

interface OrderRepo {
    fun getIngredientList(isNetwork: Boolean): Single<List<Ingredient>>
    fun getOrderList(): Single<List<OrderList>>
    fun getIngredientListByName(name: String) : Single<List<Data>>
}