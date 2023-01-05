package uz.gita.fastfoodorder.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.directions.MainDirections
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.view.add_category.AddCategoryScreen
import uz.gita.fastfoodorder.view.addorder.AddOrderScreen
import javax.inject.Inject

class MainDirectionsImpl @Inject constructor() : MainDirections {
    override lateinit var navigator: Navigator

    override suspend fun navigateToAddOrders() {
        navigator.push(AddOrderScreen())
    }

    override suspend fun navigateToAddCategory() {
        navigator.push(AddCategoryScreen())
    }

    override suspend fun back() {
        navigator.pop()
    }

}