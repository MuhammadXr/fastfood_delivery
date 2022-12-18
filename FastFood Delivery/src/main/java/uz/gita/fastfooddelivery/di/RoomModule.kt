package uz.gita.fastfooddelivery.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.fastfooddelivery.database.room.AppDataBase

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    fun provideRoom(@ApplicationContext context: Context): RoomDatabase{
        return Room.databaseBuilder(context, AppDataBase::class.java, "food_database").build()
    }
}