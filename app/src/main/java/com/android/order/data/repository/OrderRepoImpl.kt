package com.android.order.data.repository

import com.android.order.data.mapper.IngredientListResponseMapper
import com.android.order.data.mapper.OrderListResponseMapper
import com.android.order.data.remote.api.service.ApiHelper
import com.android.order.data.remote.db.service.DbHelper
import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient
import com.android.order.domain.model.OrderList
import io.reactivex.Single

class OrderRepoImpl(private val apiHelper: ApiHelper, private val dbHelper: DbHelper) : OrderRepo {

    override fun getIngredientList(isNetwork : Boolean): Single<List<Ingredient>> {
        return if (isNetwork) {
            apiHelper.getIngredientList().map { IngredientListResponseMapper.mapIngredientResponseToIngredient.invoke(it) }.doOnSuccess {
                dbHelper.saveAllIngredient(it)
            }
        } else {
            dbHelper.queryAllIngredient()
        }
    }

    override fun getOrderList(): Single<List<OrderList>> {
        return apiHelper.getOrderList().map{
            OrderListResponseMapper.mapOrderListResponseToOrderList.invoke(it)
        }
    }

    override fun getIngredientListByName(name: String): Single<List<Data>> {
        return dbHelper.searchIngredientByName(name)
    }
}