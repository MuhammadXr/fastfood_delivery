package uz.gita.fastfoodorder.view.orders.viewmodel

import uz.gita.core.data.models.OrderData
import uz.gita.core.data.models.ProductData
import uz.gita.fastfoodorder.AppViewModel

interface OrdersViewModel : AppViewModel<OrdersIntent, OrdersUiState, Nothing> {
}

sealed interface OrdersIntent {
    object Back: OrdersIntent
}

data class OrdersUiState(
    val ordersList: List<OrderData> = emptyList()
)
