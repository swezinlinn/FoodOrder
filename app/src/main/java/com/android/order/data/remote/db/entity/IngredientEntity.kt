package com.android.order.data.remote.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ingredient")
class IngredientEntity (
    var category: String = "",
    @PrimaryKey var categoryId: String = "")