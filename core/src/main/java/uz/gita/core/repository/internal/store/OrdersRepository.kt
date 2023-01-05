package uz.gita.core.repository.internal.store

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.OrderData

interface OrdersRepository {
    fun placingOrder(orderData: OrderData) : Flow<Result<Unit>>
    fun getOrders() : Flow<Result<List<OrderData>>>
    fun getOrdersRealTime(): Flow<List<OrderData>>
}