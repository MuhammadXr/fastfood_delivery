package uz.gita.fastfoodorder.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.directions.AddOrderDirections
import javax.inject.Inject

class AddOrderDirectionsImpl @Inject constructor() : AddOrderDirections {
    override lateinit var navigator: Navigator

    override suspend fun back() {
        navigator.pop()
    }
}