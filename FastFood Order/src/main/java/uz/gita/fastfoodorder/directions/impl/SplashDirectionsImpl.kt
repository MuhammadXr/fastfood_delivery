package uz.gita.fastfoodorder.directions.impl

import uz.gita.fastfoodorder.directions.SplashDirections
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.view.auth_ui.login.SignInScreen
import uz.gita.fastfoodorder.view.tab_navigation.TabScreen
import javax.inject.Inject

class SplashDirectionsImpl @Inject constructor(private val nav: AppNavigation): SplashDirections {

    override suspend fun navigateToLogin() {
        nav.replaceTo(SignInScreen())
    }

    override suspend fun navigateToMainTab() {
        nav.replaceTo(TabScreen())
    }
}