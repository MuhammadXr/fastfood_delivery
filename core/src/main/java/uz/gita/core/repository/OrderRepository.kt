package uz.gita.core.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.OrderData

interface OrderRepository {
    fun placingOrder() : Flow<Result<Unit>>
    fun getOrders() : Flow<Result<List<OrderData>>>
    fun getOrdersRealTime(): Flow<Result<List<OrderData>>>
}