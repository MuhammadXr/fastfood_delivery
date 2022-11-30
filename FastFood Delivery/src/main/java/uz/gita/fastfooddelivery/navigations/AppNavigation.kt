package uz.gita.fastfooddelivery.navigations

import cafe.adriel.voyager.androidx.AndroidScreen


typealias AppScreen = AndroidScreen

interface AppNavigation {
    suspend fun back()
    suspend fun backAll()
    suspend fun backToRoot()
    suspend fun navigateTo(screen: AppScreen)
}