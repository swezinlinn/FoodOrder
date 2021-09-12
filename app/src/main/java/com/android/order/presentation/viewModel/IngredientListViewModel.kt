package com.android.order.presentation.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.android.order.domain.model.Data
import com.android.order.domain.model.Ingredient
import com.android.order.domain.usecase.GetIngredientDataByName
import com.android.order.domain.usecase.GetIngredientList
import com.android.order.presentation.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class IngredientListViewModel @ViewModelInject
constructor(
    private val getIngredientList: GetIngredientList,
    private val getIngredientDataByName: GetIngredientDataByName) : BaseViewModel() {

    private val TAG = IngredientListViewModel::class.java.name
    val ingredient = MutableLiveData<List<Ingredient>>()
    val loadingState = MutableLiveData<Boolean>()
    val ingredientData = MutableLiveData<List<Data>>()

    fun getIngredient(isNetwork : Boolean) {
        loadingState.value = true
        getIngredientList.execute(isNetwork).subscribeBy(
            onSuccess = { loadingState.value = false
                ingredient.value = it},
            onError = { loadingState.value = false }
        ).addTo(compositeDisposal)
    }

    fun getIngredientListByName(name: String){
        getIngredientDataByName.execute(name).subscribeBy(
            onSuccess = {
                ingredientData.value = it}
        ).addTo(compositeDisposal)
    }
}