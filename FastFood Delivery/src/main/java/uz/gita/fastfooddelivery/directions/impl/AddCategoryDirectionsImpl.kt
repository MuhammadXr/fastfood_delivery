package uz.gita.fastfooddelivery.directions.impl

import uz.gita.fastfooddelivery.directions.AddCategoryDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import javax.inject.Inject

class AddCategoryDirectionsImpl @Inject constructor(private val appNavigation: AppNavigation) : AddCategoryDirections {
    override suspend fun back() {
        appNavigation.back()
    }
}