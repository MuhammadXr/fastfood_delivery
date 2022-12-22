package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.Repository
import uz.gita.fastfoodorder.ui.main.viewmodel.Intent
import uz.gita.fastfoodorder.ui.main.viewmodel.MainScreenViewModel
import uz.gita.fastfoodorder.ui.main.viewmodel.UiState

class MainScreenViewModelImpl : MainScreenViewModel, ViewModel() {

    private val rep = Repository.orderRepository
    val uiState = UiState(emptyList())

    override val container: Container<UiState, Nothing> = container(uiState){
        requestOrders()
    }

    override fun onEventDispatcher(intent: Intent) {

    }

    private fun requestOrders(): Unit = intent{
        viewModelScope.launch {
            rep.getOrdersRealTime()
                .onEach {
                    state.copy(orderList = it)
                }
                .collect()
        }
    }
}