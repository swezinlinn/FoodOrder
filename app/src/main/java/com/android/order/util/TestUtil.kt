package com.android.order.util

import com.android.order.data.remote.db.entity.IngredientEntity
import com.android.order.data.remote.db.entity.IngredientEntityData
import com.android.order.domain.model.*

object TestUtil {

    fun createIngredientList(id: Int): List<IngredientEntity> {
    val tempList = mutableListOf<IngredientEntity>()
    tempList.add(IngredientEntity( "Breakfast","4"))
    return tempList
    }

    fun createIngredientData(id: String): List<IngredientEntityData>{
        val tempList = mutableListOf<IngredientEntityData>()
        tempList.add(IngredientEntityData(
            id= 11,
            name= "Burger" ,
            image= "",
        availableQuantity = 12,
        categoryId = "4"))
        return tempList
    }

    fun createIngredientListResponse(): List<Ingredient> {
        val tempList = mutableListOf<Ingredient>()
        tempList.add(Ingredient( "Breakfast","4", createIngredientDataResponse()))
        return tempList
    }

    fun createIngredientDataResponse() : List<Data>{
        val tempList = mutableListOf<Data>()
        tempList.add(Data( 0,"Burger","",12))
        return tempList
    }

    fun createOrderListResponse(): List<OrderList> {
        val tempList = mutableListOf<OrderList>()
        tempList.add(OrderList( 0,createOrderItemResponse(),4,"2021-09-12T15:00:00.00Z","2021-09-12T22:00:00.00Z","2021-09-12T22:00:00.00Z"))
        return tempList
    }

    private fun createOrderItemResponse(): List<Item> {
        val tempList = mutableListOf<Item>()
        tempList.add(Item( "Breakfast",4, createAddOn()))
        return tempList
    }

    private fun createAddOn(): List<Addon>{
        val tempList = mutableListOf<Addon>()
        tempList.add(Addon( 0,"Egg", 2))
        return tempList
    }

}