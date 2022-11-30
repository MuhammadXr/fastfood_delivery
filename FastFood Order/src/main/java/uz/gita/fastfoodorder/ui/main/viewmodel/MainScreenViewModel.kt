package uz.gita.fastfoodorder.ui.main.viewmodel

import uz.gita.core.data.models.OrderData
import uz.gita.fastfoodorder.AppViewModel

interface MainScreenViewModel: AppViewModel<Intent, UiState, Nothing> {

}

sealed interface Intent{
    object Order: Intent
    object Back: Intent
}

data class UiState(
    var orderList: List<OrderData>
)