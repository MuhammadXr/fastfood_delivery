package uz.gita.fastfooddelivery.directions

import cafe.adriel.voyager.navigator.Navigator

interface AddCategoryDirections {

    var navigator: Navigator

    suspend fun back()
}