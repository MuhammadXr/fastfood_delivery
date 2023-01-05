package uz.gita.fastfoodorder.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.directions.AddProductDirections
import javax.inject.Inject

class AddProductDirectionsImpl @Inject constructor() : AddProductDirections {
    override lateinit var navigator: Navigator

    override suspend fun back() {
        navigator.pop()
    }
}