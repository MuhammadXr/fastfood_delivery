package uz.gita.fastfoodorder.directions

import cafe.adriel.voyager.navigator.Navigator

interface SignInDirections {

    suspend fun navigateToVerify(phoneNumber: String)
    suspend fun back()
}