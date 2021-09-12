package com.android.order.domain.usecase

import com.android.order.domain.usecase.base.SingleUseCase
import com.android.order.data.repository.OrderRepo
import com.android.order.domain.model.Data
import io.reactivex.Single
import javax.inject.Inject

class GetIngredientDataByName @Inject constructor(private val orderRepo: OrderRepo) : SingleUseCase<List<Data>, String>() {

    override fun provideSingle(params: String): Single<List<Data>> {
        return orderRepo.getIngredientListByName(params)
    }
}