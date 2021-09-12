package com.android.order.data.mapper
import com.android.order.data.model.OrderListResponse
import com.android.order.domain.model.*

class OrderListResponseMapper {
    companion object {
        val mapOrderListResponseToOrderList: ((OrderListResponse) -> (List<OrderList>)) = {
            val tempList = mutableListOf<OrderList>()
            if (it.data != null) {
                for (value in it.data) {
                    if (value != null) {
                        tempList.add(
                            OrderList(
                                value.id ?: 0,
                                value.item?.map { mapOrderItem.invoke(it) }?: emptyList(),
                                value.quantity ?: 0,
                                value.createdAt ?: "",
                                value.alertedAt ?: "",
                                value.expiredAt?: ""

                            )
                        )
                    } else {
                        tempList.add(OrderList())
                    }
                }
            }

            tempList
        }

        private val mapOrderItem: ((OrderListResponse.Data.Item) -> (Item)) = {
            Item(it.title, it.quantity, it.addon?.map { mapAddOn.invoke(it) }?: emptyList())
        }

        private val mapAddOn: ((OrderListResponse.Data.Item.Addon) -> (Addon)) = {
            Addon(it.id?: 0, it.title?: "", it.quantity?: 0 )
        }
    }
}