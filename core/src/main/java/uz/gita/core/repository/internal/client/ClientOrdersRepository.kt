package uz.gita.core.repository.internal.client

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.OrderData
import uz.gita.core.data.models.ProductData

interface ClientOrdersRepository {
    fun placingOrder(orderData: OrderData): Flow<Result<Unit>>
    fun getAllProducts(): Flow<List<ProductData>>
}