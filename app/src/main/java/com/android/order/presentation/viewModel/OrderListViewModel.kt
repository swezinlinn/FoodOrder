package com.android.order.presentation.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.android.order.domain.model.OrderList
import com.android.order.domain.usecase.GetOrderList
import com.android.order.presentation.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class OrderListViewModel @ViewModelInject constructor(private val getOrderList: GetOrderList) : BaseViewModel() {

    private val TAG = OrderListViewModel::class.java.name
    val orderList = MutableLiveData<List<OrderList>>()
    val loadingState = MutableLiveData<Boolean>()


    fun getOrderList() {
        loadingState.value = true
        getOrderList.execute(Unit).subscribeBy(
            onSuccess = {
                loadingState.value = false
                orderList.value = it
                Log.d("TAG","item++++++"+it)
            },
            onError = { loadingState.value = false
                Log.d("TAG","item++++++"+it.localizedMessage)
            }
        ).addTo(compositeDisposal)
    }
}