package uz.gita.fastfooddelivery.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfooddelivery.directions.MainDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.view.add_category.AddCategoryScreen
import uz.gita.fastfooddelivery.view.addorder.AddOrderScreen
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