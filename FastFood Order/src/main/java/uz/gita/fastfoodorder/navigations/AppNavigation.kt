package uz.gita.fastfoodorder.navigations

import cafe.adriel.voyager.androidx.AndroidScreen


typealias AppScreen = AndroidScreen

interface AppNavigation {
    suspend fun back()
    suspend fun backAll()
    suspend fun backToRoot()
    suspend fun navigateTo(screen: AppScreen)
    suspend fun replaceTo(screen: AppScreen)
}