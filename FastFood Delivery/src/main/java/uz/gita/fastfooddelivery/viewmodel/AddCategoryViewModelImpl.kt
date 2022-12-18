package uz.gita.fastfooddelivery.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.Repository
import uz.gita.fastfooddelivery.directions.AddCategoryDirections
import uz.gita.fastfooddelivery.utils.MyToast
import uz.gita.fastfooddelivery.view.add_category.viewmodel.A_C_UiState
import uz.gita.fastfooddelivery.view.add_category.viewmodel.AddCategoryIntent
import uz.gita.fastfooddelivery.view.add_category.viewmodel.AddCategoryViewModel
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModelImpl @Inject constructor(
    private val directions: AddCategoryDirections,
    private val myToast: MyToast
) : AddCategoryViewModel, ViewModel() {

    private val repository = Repository.categoriesRepository

    override val container: Container<A_C_UiState, Nothing> = container(A_C_UiState())

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