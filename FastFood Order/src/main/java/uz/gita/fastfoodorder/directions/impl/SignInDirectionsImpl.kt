package uz.gita.fastfoodorder.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.directions.SignInDirections
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.view.auth_ui.verification.VerificationScreen
import javax.inject.Inject

class SignInDirectionsImpl @Inject constructor(private val navigator: AppNavigation) : SignInDirections {

    override suspend fun navigateToVerify(phoneNumber:String) {
        navigator.navigateTo(VerificationScreen(phoneNumber))
    }

    override suspend fun back() {
        navigator.back()
    }
}