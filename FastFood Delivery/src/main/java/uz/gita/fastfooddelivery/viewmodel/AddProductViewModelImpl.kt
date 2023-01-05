package uz.gita.fastfooddelivery.viewmodel

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.data.models.ProductData
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfooddelivery.directions.AddProductDirections
import uz.gita.fastfooddelivery.domain.usecases.AddProductUseCase
import uz.gita.fastfooddelivery.utils.MyToast
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderIntent
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderUiState
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderViewModel
import javax.inject.Inject

@HiltViewModel
class AddProductViewModelImpl @Inject constructor(
    private val directions: AddProductDirections,
    private val useCase: AddProductUseCase,
    private val toast: MyToast
) : AddOrderViewModel, ViewModel() {

    private val orderRep = StoreRepository.productsRepository
    private val fileRep = StoreRepository.filesRepository
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
                    placeOrder(intent.productData)
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

    private fun placeOrder(productData: ProductData){
        viewModelScope.launch(Dispatchers.IO) {
            fileRep.uploadImage(productData.imgUrl.toUri(), productData.name.plus(".jpg"))
                .collect(){ result ->
                    result.run {
                        onSuccess {
                            it?.let {
                                productData.imgUrl = it.toString()
                                uploadOrderData(productData)
                            }
                        }
                        onFailure {
                            launch (Dispatchers.Main) {
                                toast.makeText(it.toString())
                            }
                        }
                    }
                }
        }

    }

    private fun uploadOrderData(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            orderRep.placingProducts(productData).collect() {
                it.run {
                    onSuccess {
                        launch (Dispatchers.Main) {
                            toast.makeText("Added")
                        }
                    }
                    onFailure {

                    }
                }

            }
        }
    }

}