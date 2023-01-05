package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfoodorder.view.delivery.viewmodel.DeliveryIntent
import uz.gita.fastfoodorder.view.delivery.viewmodel.DeliveryUiState
import uz.gita.fastfoodorder.view.delivery.viewmodel.DeliveryViewModel
import javax.inject.Inject

class DeliveryViewModelImpl @Inject constructor(

): DeliveryViewModel,ViewModel() {

    private val deliveryRep = StoreRepository.deliveryRepository

    override val container: Container<DeliveryUiState, Nothing> = container(DeliveryUiState()){
        requestData()
    }



    override fun onEventDispatcher(intent: DeliveryIntent) {

    }

    private fun requestData() = intent{
        viewModelScope.launch {
            deliveryRep.getDeliveryDataRealTime().collectLatest {
                reduce {
                    state.copy(deliveryList = it)
                }
            }
        }
    }
}