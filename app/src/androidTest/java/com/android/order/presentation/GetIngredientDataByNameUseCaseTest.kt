package com.android.order.presentation

import com.android.order.data.repository.OrderRepo
import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient
import com.android.order.domain.usecase.GetIngredientDataByName
import com.android.order.util.TestUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetIngredientDataByNameUseCaseTest {
    private lateinit var getIngredientData: GetIngredientDataByName

    private lateinit var mockRepo: OrderRepo

    @Before
    fun setUp() {
        mockRepo = mock()
        getIngredientData = GetIngredientDataByName(mockRepo)
    }

    /**
     * Check Use Case call getIngredientList function.
     * */
    @Test
    fun buildUseCaseObservable() {
        getIngredientData.provideSingle("Burger")
        verify(mockRepo).getIngredientListByName("Burger")
    }

    /**
     * Check Use Case implement successfully.
     * */
    @Test
    fun buildUseCaseObservableComplete() {
        stubOperator(Single.just(TestUtil.createIngredientDataResponse()))
        val testObserver = getIngredientData.provideSingle("Burger").test()
        testObserver.assertComplete()
    }

    /**
     * Check Use Case return successfully with data
     * */
    @Test
    fun buildUseCaseObservableReturnData() {
        val data = TestUtil.createIngredientDataResponse()
        stubOperator(Single.just(data))
        val testObserver = getIngredientData.provideSingle("Burger").test()
        testObserver.assertValue(data)
    }

    private fun stubOperator(single: Single<List<Data>>) {
        whenever(mockRepo.getIngredientListByName("Burger")).thenReturn(single)
    }
}