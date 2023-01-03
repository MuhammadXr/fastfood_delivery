package uz.gita.fastfooddelivery.directions.impl

import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.auth.FirebaseUser
import uz.gita.fastfooddelivery.directions.SplashDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.view.auth_ui.login.SignInScreen
import uz.gita.fastfooddelivery.view.main.MainScreen
import uz.gita.fastfooddelivery.view.tab_navigation.TabScreen
import javax.inject.Inject

class SplashDirectionsImpl @Inject constructor(private val nav: AppNavigation): SplashDirections {

    override suspend fun navigateToLogin() {
        nav.replaceTo(SignInScreen())
    }

    override suspend fun navigateToMainTab() {
        nav.replaceTo(TabScreen())
    }
}