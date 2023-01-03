package uz.gita.fastfooddelivery.directions

import cafe.adriel.voyager.navigator.Navigator

interface VerifyDirections {
    suspend fun navigateToRegister()
    suspend fun navigateToMainTab()
    suspend fun back()
}