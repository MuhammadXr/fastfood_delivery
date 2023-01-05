package uz.gita.fastfoodorder.directions.impl

import uz.gita.fastfoodorder.directions.RegisterDirections
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.view.tab_navigation.TabScreen
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