package uz.gita.fastfooddelivery.directions

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfooddelivery.navigations.AppScreen

interface MainDirections {

    var navigator: Navigator

    suspend fun navigateToAddOrders()

    suspend fun navigateToAddCategory()

    suspend fun back()
}