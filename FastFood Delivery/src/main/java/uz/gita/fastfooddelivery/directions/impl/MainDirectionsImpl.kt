package uz.gita.fastfooddelivery.directions.impl

import android.util.Log
import uz.gita.fastfooddelivery.directions.MainDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.navigations.AppScreen
import uz.gita.fastfooddelivery.ui.addorder.AddOrderScreen
import javax.inject.Inject

class MainDirectionsImpl @Inject constructor(private val appNavigation: AppNavigation) : MainDirections {

    override suspend fun navigateToAddDirection() {
        appNavigation.navigateTo(AddOrderScreen())
    }

    override suspend fun back() {
        appNavigation.back()
    }

}