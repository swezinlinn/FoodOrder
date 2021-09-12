package com.android.order.presentation

import com.android.order.util.TestUtil
import com.android.order.data.repository.OrderRepo
import com.android.order.domain.model.Ingredient
import com.android.order.domain.usecase.GetIngredientList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetIngredientListUseCaseTest {
    private lateinit var getIngredientList: GetIngredientList

    private lateinit var mockRepo: OrderRepo

    @Before
    fun setUp() {
        mockRepo = mock()
        getIngredientList = GetIngredientList(mockRepo)
    }

    /**
     * Check Use Case call getIngredientList function.
     * */
    @Test
    fun buildUseCaseObservable() {
        getIngredientList.provideSingle(true)
        verify(mockRepo).getIngredientList(true)
    }

    /**
     * Check Use Case implement successfully.
     * */
    @Test
    fun buildUseCaseObservableComplete() {
        stubOperator(Single.just(TestUtil.createIngredientListResponse()))
        val testObserver = getIngredientList.provideSingle(true).test()
        testObserver.assertComplete()
    }

    /**
     * Check Use Case return successfully with data
     * */
    @Test
    fun buildUseCaseObservableReturnData() {
        val data = TestUtil.createIngredientListResponse()
        stubOperator(Single.just(data))
        val testObserver = getIngredientList.provideSingle(true).test()
        testObserver.assertValue(data)
    }

    private fun stubOperator(single: Single<List<Ingredient>>) {
        whenever(mockRepo.getIngredientList(true)).thenReturn(single)
    }
}