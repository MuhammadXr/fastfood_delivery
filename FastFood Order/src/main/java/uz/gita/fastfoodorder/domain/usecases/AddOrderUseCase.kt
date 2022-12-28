package uz.gita.fastfoodorder.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData

interface AddOrderUseCase {
    fun getCategories(): Flow<List<CategoryData>>
    fun addOrder(orderData: OrderData): Flow<Result<Unit>>
}