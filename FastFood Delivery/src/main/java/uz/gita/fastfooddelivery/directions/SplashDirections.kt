package uz.gita.fastfooddelivery.directions

import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.auth.FirebaseUser

interface SplashDirections {

    suspend fun navigateToLogin()
    suspend fun navigateToMainTab()
}