package uz.gita.fastfooddelivery.directions

import cafe.adriel.voyager.navigator.Navigator

interface AddOrderDirections {

    var navigator: Navigator

    suspend fun back()
}