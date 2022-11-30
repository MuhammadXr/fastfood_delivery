package uz.gita.fastfooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.fastfooddelivery.navigations.AppNavigation
import uz.gita.fastfooddelivery.navigations.NavigationDispatcher
import uz.gita.fastfooddelivery.navigations.NavigationHandler


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun appNavigator(dispatcher: NavigationDispatcher): AppNavigation

    @Binds
    fun navigationHandler(dispatcher: NavigationDispatcher): NavigationHandler
}