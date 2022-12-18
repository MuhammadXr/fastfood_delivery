package uz.gita.fastfooddelivery.view.addorder.viewmodel

import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.AppViewModel

interface AddOrderViewModel: AppViewModel<AddOrderIntent, AddOrderUiState, Nothing> {
}

sealed interface AddOrderIntent{
    object Back: AddOrderIntent
    class AddOrder(val orderData: OrderData): AddOrderIntent
}

data class AddOrderUiState(
    var list: List<OrderData> = emptyList()
)