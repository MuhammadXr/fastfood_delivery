package uz.gita.fastfooddelivery.view.main.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.ProductData
import uz.gita.fastfooddelivery.AppViewModel

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
    val itemsList: List<ProductData> = emptyList(),
    val categoryItems: List<CategoryData> = emptyList()
)