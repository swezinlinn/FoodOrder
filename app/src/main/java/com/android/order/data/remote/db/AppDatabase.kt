package com.android.order.data.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.order.data.remote.db.dao.IngredientDao
import com.android.order.data.remote.db.entity.IngredientEntity
import com.android.order.data.remote.db.entity.IngredientEntityData

@Database(entities = [IngredientEntity::class,IngredientEntityData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val ingredientDao: IngredientDao

    companion object {
        const val DB_NAME = "Order.db"
    }
}
