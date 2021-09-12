package com.android.order.data.remote.db.dao


import androidx.room.*
import com.android.order.data.remote.db.entity.IngredientEntity
import com.android.order.data.remote.db.entity.IngredientEntityData
import io.reactivex.Single

@Dao
interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredient(data: IngredientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredientData(data: IngredientEntityData)

    @Query("Select * from IngredientData Where INSTR(name, :name) > 0")
    fun getIngredientEntityByName(name: String): List<IngredientEntityData>

    @Query("Select * From Ingredient")
    fun queryAllIngredient(): List<IngredientEntity>

    @Query("Select * From IngredientData Where categoryId = :categoryId")
    fun queryIngredientDataByCategoryId(categoryId: String) : List<IngredientEntityData>

    @Query("Delete From Ingredient")
    fun deleteIngredient()

    @Query("Delete From IngredientData")
    fun deleteIngredientData()

}