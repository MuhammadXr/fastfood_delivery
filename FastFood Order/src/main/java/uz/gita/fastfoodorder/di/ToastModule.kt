package uz.gita.fastfoodorder.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.fastfoodorder.utils.MyToast
import uz.gita.fastfoodorder.utils.MyToastImpl

@Module
@InstallIn(ViewModelComponent::class)
class ToastModule {
    @Provides
    fun bindToast(@ApplicationContext context: Context) : MyToast = MyToastImpl(context)
}