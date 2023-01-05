package uz.gita.fastfooddelivery.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.ProductData

interface MainUseCase {
    suspend fun getOrdersList(): Flow<List<ProductData>>
    suspend fun getCategories(): Flow<List<CategoryData>>
    suspend fun getAllList(): Flow<List<java.io.Serializable>>
}