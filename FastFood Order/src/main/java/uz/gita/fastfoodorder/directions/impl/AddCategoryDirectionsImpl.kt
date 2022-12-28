package uz.gita.fastfoodorder.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.directions.AddCategoryDirections
import javax.inject.Inject

class AddCategoryDirectionsImpl @Inject constructor() : AddCategoryDirections {
    override lateinit var navigator: Navigator

    override suspend fun back() {
        navigator.pop()
    }
}