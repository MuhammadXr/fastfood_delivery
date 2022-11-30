package uz.gita.fastfoodorder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.fastfoodorder.navigations.AppNavigation
import uz.gita.fastfoodorder.navigations.NavigationDispatcher
import uz.gita.fastfoodorder.navigations.NavigationHandler


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun appNavigator(dispatcher: NavigationDispatcher): AppNavigation

    @Binds
    fun navigationHandler(dispatcher: NavigationDispatcher): NavigationHandler
}