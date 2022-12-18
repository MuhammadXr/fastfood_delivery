package uz.gita.fastfooddelivery.view.main.viewmodel

import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.AppViewModel

interface MainScreenViewModel: AppViewModel<MainIntent, UiState, Nothing> {

}

sealed interface MainIntent{
    object Order: MainIntent
    object Back: MainIntent
    object AddOrderButton: MainIntent
    object AddToCart: MainIntent
    object GotoAddCategory: MainIntent
}

data class UiState(
    val orderList: List<OrderData>,
    val categoryList: List<CategoryData>
)