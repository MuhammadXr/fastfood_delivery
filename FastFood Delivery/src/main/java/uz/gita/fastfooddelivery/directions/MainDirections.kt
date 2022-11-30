package uz.gita.fastfooddelivery.directions

import uz.gita.fastfooddelivery.navigations.AppScreen

interface MainDirections {
    suspend fun navigateToAddDirection()

    suspend fun back()
}