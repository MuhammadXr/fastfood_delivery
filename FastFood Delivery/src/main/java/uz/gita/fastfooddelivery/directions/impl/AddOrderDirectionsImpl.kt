package uz.gita.fastfooddelivery.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfooddelivery.directions.AddCategoryDirections
import uz.gita.fastfooddelivery.directions.AddOrderDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import javax.inject.Inject

class AddOrderDirectionsImpl @Inject constructor() : AddOrderDirections {
    override lateinit var navigator: Navigator

    override suspend fun back() {
        navigator.pop()
    }
}