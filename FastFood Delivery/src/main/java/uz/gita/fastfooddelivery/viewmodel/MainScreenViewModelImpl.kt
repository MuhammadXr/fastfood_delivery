package uz.gita.fastfooddelivery.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.Repository
import uz.gita.fastfooddelivery.directions.MainDirections
import uz.gita.fastfooddelivery.view.main.viewmodel.MainIntent
import uz.gita.fastfooddelivery.view.main.viewmodel.MainScreenViewModel
import uz.gita.fastfooddelivery.view.main.viewmodel.UiState
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(
    private val mainDirections: MainDirections
) : MainScreenViewModel, ViewModel() {

    private val repOrder = Repository.orderRepository
    private val repCategory = Repository.categoriesRepository
    private val uiState = UiState(emptyList(), emptyList())

    override val container: Container<UiState, Nothing> = container(uiState) {
        requestItems()
    }

    override fun onEventDispatcher(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.AddOrderButton -> {
                    mainDirections.navigateToAddDirection()
                }
                is MainIntent.Back -> {
                    mainDirections.back()
                }
                is MainIntent.Order -> {

                }
                is MainIntent.AddToCart -> {

                }
                is MainIntent.GotoAddCategory -> mainDirections.navigateToAddCategory()
            }
        }

    }

    private fun requestItems(): Unit = intent {
        viewModelScope.launch {
            launch {
                repCategory.getCategories()
                    .onEach { result ->
                        Log.d("TTT", "CATEGORIYA ADD WORKED")
                        reduce {
                            state.copy(categoryList = result)
                        }

                    }
                    .collect()
            }
            launch {
                repOrder.getOrdersRealTime()
                    .onEach {
                        Log.d("TTT", "Order ADD WORKED")
                        reduce {
                            state.copy(orderList = it)
                        }

                    }
                    .collect()
            }
        }
    }
}