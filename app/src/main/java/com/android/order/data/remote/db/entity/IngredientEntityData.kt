package com.android.order.data.remote.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "IngredientData")
data class IngredientEntityData (
    @PrimaryKey val id: Int = 0,
    var name: String,
    var image: String,
    var availableQuantity: Int,
    var categoryId : String)