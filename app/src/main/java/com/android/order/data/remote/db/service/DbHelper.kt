package com.android.order.data.remote.db.service

import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient
import io.reactivex.Single

interface DbHelper {
    fun queryAllIngredient(): Single<List<Ingredient>>
    fun searchIngredientByName(name: String): Single<List<Data>>
    fun saveAllIngredient(ingredient: List<Ingredient>)
}