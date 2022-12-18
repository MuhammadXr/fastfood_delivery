package uz.gita.fastfooddelivery.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.fastfooddelivery.database.dao.CategoryDao
import uz.gita.fastfooddelivery.database.dao.OrderDao
import uz.gita.fastfooddelivery.database.models.CategoryData
import uz.gita.fastfooddelivery.database.models.FoodData
import uz.gita.fastfooddelivery.database.models.OrderData

@Database(
    entities = [CategoryData::class, FoodData::class, OrderData::class],
    version = 0
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun orderDao(): OrderDao
}