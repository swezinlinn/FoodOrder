package com.android.order.domain.usecase.base

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


abstract class SingleUseCase<T, in Params> {
    /**
     * Implement this method in your custom UseCase in order to provide the final [Single].
     *
     * @param params The Params.
     * @return The provided [Single].
     */
    abstract fun provideSingle(params: Params): Single<T>

    /**
     * Builds the provided [Single] and performs some transformation on it.
     *
     * @return The Observable with any transformation applied.
     */
    private fun buildUseCaseSingle(): SingleTransformer<T, T> {

        return SingleTransformer { single ->
            single.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
        }
    }

    /**
     * Executes the provided [Single]
     *
     * @param params The Params.
     * @return The provided Single.
     */
    open fun execute(params: Params): Single<T> {
        val single = provideSingle(params)
        return single.compose(buildUseCaseSingle())
    }

}