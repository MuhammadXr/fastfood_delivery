package uz.gita.fastfooddelivery.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.data.models.OrderData
import uz.gita.core.repository.Repository
import uz.gita.fastfooddelivery.directions.AddOrderDirections
import uz.gita.fastfooddelivery.domain.usecases.AddOrderUseCase
import uz.gita.fastfooddelivery.utils.MyToast
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderIntent
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderUiState
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderViewModel
import javax.inject.Inject

@HiltViewModel
class AddOrderViewModelImpl @Inject constructor(
    private val directions: AddOrderDirections,
    private val useCase: AddOrderUseCase,
    private val toast: MyToast
) : AddOrderViewModel, ViewModel() {

    private val orderRep = Repository.orderRepository
    val uiState = AddOrderUiState()

    override val container: Container<AddOrderUiState, Nothing> = container(uiState) {
        requestCategories()
    }

    override fun setNavigator(navigator: Navigator) {
        directions.navigator = navigator
    }

    override fun onEventDispatcher(intent: AddOrderIntent) {
        viewModelScope.launch {
            when (intent) {
                is AddOrderIntent.AddOrder -> {
                    placeOrder(intent.orderData)
                }
                is AddOrderIntent.Back -> directions.back()
            }
        }

    }


    private fun requestCategories() = intent {
        viewModelScope.launch {
            useCase.getCategories().collect() {
                reduce {
                    state.copy(categoriesList = it)
                }
            }
        }
    }

    private fun placeOrder(orderData: OrderData) {
        viewModelScope.launch {
            orderRep.placingOrder(orderData).collect() {
                Log.d("TTT", "Result worked")
                it.run {
                    Log.d("TTT", "Result worked")
                    onSuccess {
                        toast.makeText("Added")
                    }
                    onFailure {

                    }
                }

            }
        }
    }

}