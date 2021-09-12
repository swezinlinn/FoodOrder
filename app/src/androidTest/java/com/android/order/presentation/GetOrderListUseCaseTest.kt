package com.android.order.presentation

import com.android.order.data.repository.OrderRepo
import com.android.order.domain.model.OrderList
import com.android.order.domain.usecase.GetOrderList
import com.android.order.util.TestUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetOrderListUseCaseTest {
    private lateinit var getOrderList: GetOrderList

    private lateinit var mockRepo: OrderRepo

    @Before
    fun setUp() {
        mockRepo = mock()
        getOrderList = GetOrderList(mockRepo)
    }

    /**
     * Check Use Case call getIngredientList function.
     * */
    @Test
    fun buildUseCaseObservable() {
        getOrderList.provideSingle(Unit)
        verify(mockRepo).getOrderList()
    }

    /**
     * Check Use Case implement successfully.
     * */
    @Test
    fun buildUseCaseObservableComplete() {
        stubOperator(Single.just(TestUtil.createOrderListResponse()))
        val testObserver = getOrderList.provideSingle(Unit).test()
        testObserver.assertComplete()
    }

    /**
     * Check Use Case return successfully with data
     * */
    @Test
    fun buildUseCaseObservableReturnData() {
        val data = TestUtil.createOrderListResponse()
        stubOperator(Single.just(data))
        val testObserver = getOrderList.provideSingle(Unit).test()
        testObserver.assertValue(data)
    }

    private fun stubOperator(single: Single<List<OrderList>>) {
        whenever(mockRepo.getOrderList()).thenReturn(single)
    }
}