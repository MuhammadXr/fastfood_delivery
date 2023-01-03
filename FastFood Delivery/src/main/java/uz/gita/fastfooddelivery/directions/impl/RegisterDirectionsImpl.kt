package uz.gita.fastfooddelivery.directions.impl

import uz.gita.fastfooddelivery.directions.RegisterDirections
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.view.tab_navigation.TabScreen
import javax.inject.Inject

class RegisterDirectionsImpl @Inject constructor(
    private val navigation: AppNavigation
) : RegisterDirections {
    override suspend fun navigateToMainTab() {
        navigation.backAll()
        navigation.navigateTo(TabScreen())
    }

    override suspend fun back() {
        navigation.back()
    }
}