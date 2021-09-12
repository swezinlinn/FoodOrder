package com.android.order.domain.usecase

import com.android.order.domain.usecase.base.SingleUseCase
import com.android.order.data.repository.OrderRepo
import com.android.order.domain.model.OrderList
import io.reactivex.Single
import javax.inject.Inject

class GetOrderList @Inject constructor(private val orderRepo: OrderRepo) : SingleUseCase<List<OrderList>, Unit>() {

    override fun provideSingle(params: Unit): Single<List<OrderList>> {
        return orderRepo.getOrderList()
    }
}