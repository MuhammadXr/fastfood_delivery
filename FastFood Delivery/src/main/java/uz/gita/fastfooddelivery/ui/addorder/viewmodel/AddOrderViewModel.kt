package uz.gita.fastfooddelivery.ui.addorder.viewmodel

import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.AppViewModel
import uz.gita.fastfooddelivery.ui.main.viewmodel.Intent

interface AddOrderViewModel: AppViewModel<AddOrderIntent, AddOrderUiState, Nothing> {
}

sealed interface AddOrderIntent{
    object Back: AddOrderIntent
    class AddOrder(val orderData: OrderData): AddOrderIntent
}

data class AddOrderUiState(
    var list: List<OrderData> = emptyList()
)