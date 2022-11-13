package uz.gita.core.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.FoodData
import uz.gita.core.repository.ItemsRepository

internal class ItemsRepositoryImpl : ItemsRepository {
    override fun addItems(foodData: FoodData): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteItems(foodData: FoodData): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun update(foodData: FoodData): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAllFoods(): Flow<Result<List<FoodData>>> {
        TODO("Not yet implemented")
    }
}