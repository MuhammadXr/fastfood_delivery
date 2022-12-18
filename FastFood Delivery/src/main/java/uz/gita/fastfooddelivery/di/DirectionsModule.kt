package uz.gita.fastfooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.fastfooddelivery.directions.AddCategoryDirections
import uz.gita.fastfooddelivery.directions.MainDirections
import uz.gita.fastfooddelivery.directions.impl.AddCategoryDirectionsImpl
import uz.gita.fastfooddelivery.directions.impl.MainDirectionsImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {
    @Binds
    fun mainDirection(impl: MainDirectionsImpl): MainDirections

    @Binds
    fun addCategoryDirection(impl: AddCategoryDirectionsImpl): AddCategoryDirections
}