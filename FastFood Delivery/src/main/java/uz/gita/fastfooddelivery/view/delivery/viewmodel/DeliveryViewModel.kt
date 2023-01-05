package uz.gita.fastfooddelivery.view.delivery.viewmodel

import uz.gita.core.data.models.DeliveryData
import uz.gita.fastfooddelivery.AppViewModel

interface DeliveryViewModel: AppViewModel<DeliveryIntent, DeliveryUiState, Nothing> {
}

sealed interface DeliveryIntent {

}

data class DeliveryUiState (
    val deliveryList: List<DeliveryData> = emptyList()
)
