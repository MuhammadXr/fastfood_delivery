package uz.gita.fastfooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.fastfooddelivery.directions.*
import uz.gita.fastfooddelivery.directions.impl.*

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {
    @Binds
    fun mainDirection(impl: MainDirectionsImpl): MainDirections

    @Binds
    fun addCategoryDirection(impl: AddCategoryDirectionsImpl): AddCategoryDirections

    @Binds
    fun addOrderDirection(impl: AddOrderDirectionsImpl): AddOrderDirections

    @Binds
    fun signInDirection(impl: SignInDirectionsImpl): SignInDirections

    @Binds
    fun verifyDirections(impl: VerifyDirectionsImpl): VerifyDirections

    @Binds
    fun splashDirections(impl: SplashDirectionsImpl): SplashDirections

    @Binds
    fun profileDirections(impl: ProfileDirectionsImpl): ProfileDirections

    @Binds
    fun registerDirections(impl: RegisterDirectionsImpl): RegisterDirections
}