package uz.gita.fastfoodorder.view.add_category.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.AppViewModel

interface AddCategoryViewModel: AppViewModel<AddCategoryIntent, A_C_UiState, Nothing>{
    fun setNavigator(navigator: Navigator)
}

sealed interface AddCategoryIntent{
    object Back: AddCategoryIntent
    class Add(val name: String): AddCategoryIntent
}

data class A_C_UiState(
    var state: Boolean = false
)