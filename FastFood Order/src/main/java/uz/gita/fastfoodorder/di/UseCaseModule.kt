package uz.gita.fastfooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.fastfoodorder.domain.usecases.AddOrderUseCase
import uz.gita.fastfoodorder.domain.usecases.MainUseCase
import uz.gita.fastfoodorder.domain.usecases.impl.AddOrderUseCaseImpl
import uz.gita.fastfoodorder.domain.usecases.impl.MainUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    fun bindAddOrderUseCase(impl: AddOrderUseCaseImpl): AddOrderUseCase
}