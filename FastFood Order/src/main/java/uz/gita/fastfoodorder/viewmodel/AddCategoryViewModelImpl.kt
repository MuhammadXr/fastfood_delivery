package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfoodorder.directions.AddCategoryDirections
import uz.gita.fastfoodorder.utils.MyToast
import uz.gita.fastfoodorder.view.add_category.viewmodel.A_C_UiState
import uz.gita.fastfoodorder.view.add_category.viewmodel.AddCategoryIntent
import uz.gita.fastfoodorder.view.add_category.viewmodel.AddCategoryViewModel
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModelImpl @Inject constructor(
    private val directions: AddCategoryDirections,
    private val myToast: MyToast
) : AddCategoryViewModel, ViewModel() {

    private val repository = StoreRepository.categoriesRepository

    override val container: Container<A_C_UiState, Nothing> = container(A_C_UiState())
    override fun setNavigator(navigator: Navigator) {
        directions.navigator = navigator
    }

    override fun onEventDispatcher(intent: AddCategoryIntent) {
        viewModelScope.launch {
            when (intent) {
                is AddCategoryIntent.Add -> {
                    addCategory(intent.name)
                    myToast.makeText("Added To Queue")
                }
                is AddCategoryIntent.Back -> directions.back()
            }
        }

    }

    private fun addCategory(name: String) {
        val categoryData = CategoryData(name = name)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(categoryData)
                .onEach { myToast.makeText("Added") }
                .collect()
        }
    }

}