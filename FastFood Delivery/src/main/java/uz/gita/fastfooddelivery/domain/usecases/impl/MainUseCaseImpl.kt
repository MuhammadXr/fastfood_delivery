package uz.gita.fastfooddelivery.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.core.repository.DeliveryRepository
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.domain.usecases.MainUseCase
import java.io.Serializable
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor() : MainUseCase {
    private val orderRepository = DeliveryRepository.orderRepository
    private val categoriesRepository = DeliveryRepository.categoriesRepository

    override suspend fun getOrdersList(): Flow<List<OrderData>> {
        return orderRepository.getOrdersRealTime()
    }

    override suspend fun getCategories(): Flow<List<CategoryData>> {
        return categoriesRepository.getCategories()
    }

    override suspend fun getAllList(): Flow<List<Serializable>> = flow{
        val mergedList = ArrayList<Serializable>()

        categoriesRepository.apply {
            getCategories()
                .collect(){
                    mergedList.addAll(it)
                }
        }
        orderRepository.apply {
            getOrdersRealTime()
                .collect(){
                    mergedList.addAll(it)
                }
        }

        emit(mergedList)

    }


}