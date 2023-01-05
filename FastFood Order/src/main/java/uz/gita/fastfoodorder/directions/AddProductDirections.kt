package uz.gita.fastfoodorder.directions

import cafe.adriel.voyager.navigator.Navigator

interface AddProductDirections {

    var navigator: Navigator

    suspend fun back()
}