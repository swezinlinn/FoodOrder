package com.android.order.data.mapper

import com.android.order.data.model.IngredientResponse
import com.android.order.domain.model.*

class IngredientListResponseMapper {
    companion object {
        val mapIngredientResponseToIngredient: ((IngredientResponse) -> (List<Ingredient>)) = {
            val tempList = mutableListOf<Ingredient>()
            if (it.ingredient != null) {
                for (value in it.ingredient!!) {
                    tempList.add(
                        Ingredient(
                            value.category ?: "",
                            value.categoryId ?: "",
                            value.list?.map { mapData.invoke(it) } ?: emptyList()

                        )
                    )
                }
            }

            tempList
        }

        private val mapData: ((IngredientResponse.Data) -> (Data)) = {
            Data(
                it.id ?: 0,
                it.name ?: "",
                it.image ?: "",
                it.availableQuantity ?: 0
            )
        }

    }
}