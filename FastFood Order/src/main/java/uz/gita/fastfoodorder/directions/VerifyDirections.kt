package uz.gita.fastfoodorder.directions

import cafe.adriel.voyager.navigator.Navigator

interface VerifyDirections {
    suspend fun navigateToRegister()
    suspend fun navigateToMainTab()
    suspend fun back()
}