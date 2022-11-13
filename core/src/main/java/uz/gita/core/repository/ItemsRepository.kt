package uz.gita.core.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.FoodData

interface ItemsRepository {
    fun addItems(foodData: FoodData): Flow<Result<Unit>>
    fun deleteItems(foodData: FoodData): Flow<Result<Unit>>
    fun update(foodData: FoodData):Flow<Result<Unit>>
    fun getAllFoods(): Flow<Result<List<FoodData>>>
}