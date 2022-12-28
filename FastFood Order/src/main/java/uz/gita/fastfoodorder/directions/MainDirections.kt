package uz.gita.fastfoodorder.directions

import cafe.adriel.voyager.navigator.Navigator

interface MainDirections {

    var navigator: Navigator

    suspend fun navigateToAddOrders()

    suspend fun navigateToAddCategory()

    suspend fun back()
}