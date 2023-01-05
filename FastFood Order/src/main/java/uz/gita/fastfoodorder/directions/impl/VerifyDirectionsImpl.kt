package uz.gita.fastfoodorder.directions.impl

import uz.gita.fastfoodorder.directions.VerifyDirections
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.view.auth_ui.registration.RegistrationScreen
import uz.gita.fastfoodorder.view.tab_navigation.TabScreen
import javax.inject.Inject

class VerifyDirectionsImpl @Inject constructor(private val navigator: AppNavigation) : VerifyDirections {

    override suspend fun navigateToRegister() {
        navigator.navigateTo(RegistrationScreen())
    }

    override suspend fun navigateToMainTab() {
        navigator.navigateTo(TabScreen())
    }

    override suspend fun back() {
        navigator.back()
    }
}