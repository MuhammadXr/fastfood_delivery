package uz.gita.fastfoodorder.di//package uz.gita.fastfooddelivery.di
//
//import android.content.Context
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import uz.gita.fastfooddelivery.database.dao.CategoryDao
//import uz.gita.fastfooddelivery.database.dao.OrderDao
//import uz.gita.fastfooddelivery.database.room.AppDataBase
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RoomModule {
//    @[Provides Singleton]
//    fun provideRoom(@ApplicationContext context: Context): AppDataBase{
//        return Room.databaseBuilder(context, AppDataBase::class.java, "food_database").build()
//    }
//
//    @[Provides Singleton]
//    fun provideOrderDao(appDataBase: AppDataBase): OrderDao = appDataBase.orderDao()
//
//    @[Provides Singleton]
//    fun provideCategoriesDao(appDataBase: AppDataBase): CategoryDao = appDataBase.categoryDao()
//}