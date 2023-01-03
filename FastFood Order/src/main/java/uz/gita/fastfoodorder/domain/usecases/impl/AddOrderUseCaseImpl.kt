package uz.gita.fastfoodorder.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData
import uz.gita.core.repository.DeliveryRepository
import uz.gita.fastfoodorder.domain.usecases.AddOrderUseCase
import javax.inject.Inject

class AddOrderUseCaseImpl @Inject constructor(): AddOrderUseCase {

    private val categoryRep = DeliveryRepository.categoriesRepository
    private val orderRep = DeliveryRepository.orderRepository

    override fun getCategories(): Flow<List<CategoryData>> = categoryRep.getCategories()

    override fun addOrder(orderData: OrderData): Flow<Result<Unit>> = orderRep.placingOrder(orderData)
}