package uz.gita.fastfooddelivery.ui.main.viewmodel

import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.AppViewModel

interface MainScreenViewModel: AppViewModel<Intent, UiState, Nothing> {

}

sealed interface Intent{
    object Order: Intent
    object Back: Intent
    object AddOrderButton: Intent
}

data class UiState(
    val orderList: List<OrderData>
)