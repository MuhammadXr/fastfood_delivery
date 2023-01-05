package uz.gita.fastfooddelivery.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfooddelivery.directions.AddProductDirections
import javax.inject.Inject

class AddProductDirectionsImpl @Inject constructor() : AddProductDirections {
    override lateinit var navigator: Navigator

    override suspend fun back() {
        navigator.pop()
    }
}