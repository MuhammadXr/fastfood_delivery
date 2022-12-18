package uz.gita.fastfooddelivery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.Repository
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderIntent
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderUiState
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderViewModel


class AddOrderViewModelImpl : AddOrderViewModel, ViewModel() {

    private val orderRep = Repository.orderRepository
    val uiState = AddOrderUiState()

    override val container: Container<AddOrderUiState, Nothing> = container(uiState){

    }

    override fun onEventDispatcher(intent: AddOrderIntent) {
        viewModelScope.launch {
            when(intent){
                is AddOrderIntent.AddOrder -> {
                    orderRep.placingOrder(intent.orderData).collect()
                }
                else -> {}
            }
        }
    }


}