package uz.gita.core.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.OrderData
import uz.gita.core.repository.OrderRepository

internal class OrderRepositoryImpl : OrderRepository {
    override fun placingOrder(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getOrders(): Flow<Result<List<OrderData>>> {
        TODO("Not yet implemented")
    }

    override fun getOrdersRealTime(): Flow<Result<List<OrderData>>> {
        TODO("Not yet implemented")
    }
}