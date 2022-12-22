package uz.gita.fastfooddelivery.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData

interface MainUseCase {
    suspend fun getOrdersList(): Flow<List<OrderData>>
    suspend fun getCategories(): Flow<List<CategoryData>>
    suspend fun getAllList(): Flow<List<java.io.Serializable>>
}