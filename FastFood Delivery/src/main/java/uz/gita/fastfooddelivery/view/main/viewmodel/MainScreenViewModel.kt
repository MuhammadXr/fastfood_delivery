package uz.gita.fastfooddelivery.view.main.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.AppViewModel
import java.io.Serializable

interface MainScreenViewModel: AppViewModel<MainIntent, UiState, Nothing> {

    fun setNavigator(navigator: Navigator)
}

sealed interface MainIntent{
    object Order: MainIntent
    object Back: MainIntent
    object AddOrderButton: MainIntent
    object AddToCart: MainIntent
    object GotoAddCategory: MainIntent
}

data class UiState(
    val itemsList: List<OrderData> = emptyList(),
    val categoryItems: List<CategoryData> = emptyList()
)