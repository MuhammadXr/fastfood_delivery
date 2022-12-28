package uz.gita.fastfoodorder.view.addorder.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData
import uz.gita.fastfoodorder.AppViewModel

interface AddOrderViewModel: AppViewModel<AddOrderIntent, AddOrderUiState, Nothing> {
    fun setNavigator(navigator: Navigator)
}

sealed interface AddOrderIntent{
    object Back: AddOrderIntent
    class AddOrder(val orderData: OrderData): AddOrderIntent
}

data class AddOrderUiState(
    var list: List<OrderData> = emptyList(),
    var categoriesList: List<CategoryData> = emptyList()
)