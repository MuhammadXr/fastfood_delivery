package uz.gita.fastfooddelivery.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.Repository
import uz.gita.fastfooddelivery.directions.MainDirections
import uz.gita.fastfooddelivery.ui.addorder.AddOrderScreen
import uz.gita.fastfooddelivery.ui.main.viewmodel.Intent
import uz.gita.fastfooddelivery.ui.main.viewmodel.MainScreenViewModel
import uz.gita.fastfooddelivery.ui.main.viewmodel.UiState
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(
    private val mainDirections: MainDirections
) : MainScreenViewModel, ViewModel() {

    private val rep = Repository.orderRepository
    private val uiState = UiState(emptyList())

    override val container: Container<UiState, Nothing> = container(uiState){
        requestOrders()
    }

    override fun onEventDispatcher(intent: Intent) {
        viewModelScope.launch {
            when(intent) {
                is Intent.AddOrderButton -> {
                    mainDirections.navigateToAddDirection()
                }
                is Intent.Back -> {
                    mainDirections.back()
                }
                is Intent.Order -> {

                }
            }
        }

    }

    private fun requestOrders(): Unit = intent{
        viewModelScope.launch {
            rep.getOrdersRealTime()
                .onEach {
                    reduce {
                        state.copy(orderList = it)
                    }

                }
                .collect()
        }
    }
}