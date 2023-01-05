package uz.gita.fastfooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.fastfooddelivery.domain.usecases.AddProductUseCase
import uz.gita.fastfooddelivery.domain.usecases.MainUseCase
import uz.gita.fastfooddelivery.domain.usecases.impl.AddProductUseCaseImpl
import uz.gita.fastfooddelivery.domain.usecases.impl.MainUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    fun bindAddOrderUseCase(impl: AddProductUseCaseImpl): AddProductUseCase
}