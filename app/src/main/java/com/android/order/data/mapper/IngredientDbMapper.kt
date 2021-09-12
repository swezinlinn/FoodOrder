package com.android.order.data.mapper

import com.android.order.data.remote.db.entity.IngredientEntity
import com.android.order.data.remote.db.entity.IngredientEntityData
import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient

class IngredientDbMapper {
    companion object {
        val mapIngredientToEntity: ((Ingredient) -> (IngredientEntity)) = {
            IngredientEntity(it.category, it.categoryId)
        }

        val mapIngredientDataToEntity: ((Data, String) -> (IngredientEntityData)) = { data, categoryId ->
            IngredientEntityData(data.id, data.name, data.image,data.availableQuantity, categoryId)
        }

        val mapEntityToIngredient: ((IngredientEntity, List<IngredientEntityData>) -> (List<Ingredient>)) =
            { ingredient, data ->
                val tempList = mutableListOf<Ingredient>()
                tempList.add(
                    Ingredient(
                        ingredient.category ?: "",
                        ingredient.categoryId ?: "",
                        data.map { mapIngredientEntityDataToData.invoke(it) } ?: emptyList()

                    )
                )

                tempList
            }

        val mapIngredientEntityDataToData: ((IngredientEntityData) -> (Data)) = {
            Data(
                it.id ?: 0,
                it.name ?: "",
                it.image ?: "",
                it.availableQuantity ?: 0
            )
        }

    }
}