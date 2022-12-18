package uz.gita.fastfooddelivery.directions.impl

import uz.gita.fastfooddelivery.directions.MainDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.view.add_category.AddCategoryScreen
import uz.gita.fastfooddelivery.view.addorder.AddOrderScreen
import javax.inject.Inject

class MainDirectionsImpl @Inject constructor(private val appNavigation: AppNavigation) : MainDirections {

    override suspend fun navigateToAddDirection() {
        appNavigation.navigateTo(AddOrderScreen())
    }

    override suspend fun navigateToAddCategory() {
        appNavigation.navigateTo(AddCategoryScreen())
    }

    override suspend fun back() {
        appNavigation.back()
    }

}