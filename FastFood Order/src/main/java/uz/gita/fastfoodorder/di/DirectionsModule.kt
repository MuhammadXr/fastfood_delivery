package uz.gita.fastfooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.fastfoodorder.directions.AddCategoryDirections
import uz.gita.fastfoodorder.directions.AddOrderDirections
import uz.gita.fastfoodorder.directions.MainDirections
import uz.gita.fastfoodorder.directions.impl.AddCategoryDirectionsImpl
import uz.gita.fastfoodorder.directions.impl.AddOrderDirectionsImpl
import uz.gita.fastfoodorder.directions.impl.MainDirectionsImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {
    @Binds
    fun mainDirection(impl: MainDirectionsImpl): MainDirections

    @Binds
    fun addCategoryDirection(impl: AddCategoryDirectionsImpl): AddCategoryDirections

    @Binds
    fun addOrderDirection(impl: AddOrderDirectionsImpl): AddOrderDirections
}