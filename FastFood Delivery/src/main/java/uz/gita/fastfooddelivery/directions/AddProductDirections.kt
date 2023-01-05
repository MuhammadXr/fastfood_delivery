package uz.gita.fastfooddelivery.directions

import cafe.adriel.voyager.navigator.Navigator

interface AddProductDirections {

    var navigator: Navigator

    suspend fun back()
}