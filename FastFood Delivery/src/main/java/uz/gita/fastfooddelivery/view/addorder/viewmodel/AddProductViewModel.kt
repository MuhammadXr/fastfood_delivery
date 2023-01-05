package uz.gita.fastfooddelivery.view.addorder.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.ProductData
import uz.gita.fastfooddelivery.AppViewModel

interface AddOrderViewModel: AppViewModel<AddOrderIntent, AddOrderUiState, Nothing> {
    fun setNavigator(navigator: Navigator)
}

sealed interface AddOrderIntent{
    object Back: AddOrderIntent
    class AddOrder(val productData: ProductData): AddOrderIntent
}

data class AddOrderUiState(
    var list: List<ProductData> = emptyList(),
    var categoriesList: List<CategoryData> = emptyList()
)