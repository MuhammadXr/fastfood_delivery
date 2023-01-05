package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfoodorder.view.orders.viewmodel.OrdersIntent
import uz.gita.fastfoodorder.view.orders.viewmodel.OrdersUiState
import uz.gita.fastfoodorder.view.orders.viewmodel.OrdersViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersViewModelImpl @Inject constructor(

) : OrdersViewModel, ViewModel() {

    private val orderRep = StoreRepository.ordersRepository
    override val container: Container<OrdersUiState, Nothing> = container(OrdersUiState())

    override fun onEventDispatcher(intent: OrdersIntent) {

    }

    private fun requestItems(): Unit = intent {
        viewModelScope.launch {
            launch {
                orderRep.getOrdersRealTime().collectLatest {
                    reduce {
                        state.copy(ordersList = it)
                    }
                }
            }
        }
    }
}