package uz.gita.fastfooddelivery.directions.impl

import uz.gita.fastfooddelivery.directions.VerifyDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.view.auth_ui.registration.RegistrationScreen
import uz.gita.fastfooddelivery.view.tab_navigation.TabScreen
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