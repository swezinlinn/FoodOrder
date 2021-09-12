package com.android.order.data


import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.android.order.data.remote.db.AppDatabase
import com.android.order.data.remote.db.entity.IngredientEntity
import com.android.order.data.remote.db.entity.IngredientEntityData
import com.android.order.util.TestUtil
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class IngredientDaoTest {

    private lateinit var mDatabase: AppDatabase

    @Before
    fun createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getTargetContext(),
            AppDatabase::class.java
        )
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun isIngredientListEmpty() {
        assertEquals(0, mDatabase.ingredientDao.queryAllIngredient().size)
    }


    @Test
    @Throws(Exception::class)
    fun insertIngredient() {

        val ingredient: List<IngredientEntity> = TestUtil.createIngredientList(6)
        for (data in ingredient) {
            mDatabase.ingredientDao.insertIngredient(data)
        }
        assertNotEquals(0, mDatabase.ingredientDao.queryAllIngredient().size)
    }


    @Test
    @Throws(Exception::class)
    fun insertIngredientAndLoadByCategory() {
        val data: List<IngredientEntityData> = TestUtil.createIngredientData("6")
        for (item in data) {
            mDatabase.ingredientDao.insertIngredientData(item)
            val movieLoadedByName = mDatabase.ingredientDao.getIngredientEntityByName("Burger")
            assertEquals(movieLoadedByName, data)
        }
    }

}