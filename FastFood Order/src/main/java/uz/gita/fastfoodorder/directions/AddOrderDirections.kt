package uz.gita.fastfoodorder.directions

import cafe.adriel.voyager.navigator.Navigator

interface AddOrderDirections {

    var navigator: Navigator

    suspend fun back()
}