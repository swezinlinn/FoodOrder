package com.android.order.data.remote.db.service

import com.android.order.data.mapper.IngredientDbMapper
import com.android.order.data.remote.db.dao.IngredientDao
import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient
import io.reactivex.Single

class DbHelperImpl(private val dao: IngredientDao) : DbHelper{
    override fun queryAllIngredient(): Single<List<Ingredient>> {
        var model : List<Ingredient> = emptyList()
        val entities = dao.queryAllIngredient()
        for (entity in entities) {
            val bankAccounts = dao.queryIngredientDataByCategoryId(entity.categoryId)
            model = (IngredientDbMapper.mapEntityToIngredient.invoke(entity, bankAccounts))
        }
        return Single.just(model)
    }

    override fun searchIngredientByName(name: String): Single<List<Data>> {
        return Single.just(dao.getIngredientEntityByName(name).map { IngredientDbMapper.mapIngredientEntityDataToData.invoke(it) })
    }

    override fun saveAllIngredient(ingredient: List<Ingredient>) {
        dao.deleteIngredient()
        dao.deleteIngredientData()
        for (data in ingredient) {
            dao.insertIngredient(IngredientDbMapper.mapIngredientToEntity.invoke(data))
            for (ingredientData in data.list) {
                dao.insertIngredientData(IngredientDbMapper.mapIngredientDataToEntity.invoke(ingredientData,data.categoryId))
            }
        }
    }


}