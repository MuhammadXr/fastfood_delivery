package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.ClientRepository
import uz.gita.fastfoodorder.directions.MainDirections
import uz.gita.fastfoodorder.view.main.viewmodel.MainIntent
import uz.gita.fastfoodorder.view.main.viewmodel.ProductsScreenViewModel
import uz.gita.fastfoodorder.view.main.viewmodel.UiState
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModelImpl @Inject constructor(
    private val mainDirections: MainDirections,
) : ProductsScreenViewModel, ViewModel() {

    private val categoriesRepository = ClientRepository.clientCategoriesOfProductsRepository
    private val ordersRepository = ClientRepository.clientOrdersRepository

    private val uiState = UiState(emptyList())

    override val container: Container<UiState, Nothing> = container(uiState) {
        requestItems()
    }

    override fun setNavigator(navigator: Navigator){
        mainDirections.navigator = navigator
    }

    override fun onEventDispatcher(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.AddOrderButton -> mainDirections.navigateToAddOrders()

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
                ordersRepository.getAllProducts().collect(){
                    reduce {
                        state.copy(itemsList = it)
                    }
                }
            }
            launch {
                categoriesRepository.getCategories().collect(){
                    reduce {
                        state.copy(categoryItems = it)
                    }
                }
            }
        }
    }
}