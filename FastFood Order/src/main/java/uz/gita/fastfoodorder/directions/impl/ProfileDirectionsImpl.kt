package uz.gita.fastfoodorder.directions.impl

import uz.gita.fastfoodorder.directions.ProfileDirections
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.view.auth_ui.login.SignInScreen
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