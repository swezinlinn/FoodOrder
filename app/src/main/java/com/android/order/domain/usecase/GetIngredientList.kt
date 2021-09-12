package com.android.order.domain.usecase

import com.android.order.data.repository.OrderRepo
import com.android.order.domain.usecase.base.SingleUseCase
import com.android.order.domain.model.Ingredient
import io.reactivex.Single
import javax.inject.Inject

class GetIngredientList @Inject constructor(private val orderRepo: OrderRepo) : SingleUseCase<List<Ingredient>, Boolean>() {

    override fun provideSingle(params: Boolean): Single<List<Ingredient>> {
        return orderRepo.getIngredientList(params)
    }
}