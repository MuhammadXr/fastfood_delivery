package uz.gita.fastfooddelivery.directions.impl

import uz.gita.fastfooddelivery.directions.ProfileDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.view.auth_ui.login.SignInScreen
import javax.inject.Inject

class ProfileDirectionsImpl @Inject constructor(
    private val navigation: AppNavigation
): ProfileDirections {
    override suspend fun navigateToLogin() {
        navigation.backAll()
        navigation.navigateTo(SignInScreen())
    }

    override suspend fun back() {
        navigation.back()
    }
}