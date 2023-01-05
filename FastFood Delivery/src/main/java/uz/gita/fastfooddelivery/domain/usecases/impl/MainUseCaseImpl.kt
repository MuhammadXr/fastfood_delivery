package uz.gita.fastfooddelivery.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.core.repository.StoreRepository
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.ProductData
import uz.gita.fastfooddelivery.domain.usecases.MainUseCase
import java.io.Serializable
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor() : MainUseCase {
    private val orderRepository = StoreRepository.productsRepository
    private val categoriesRepository = StoreRepository.categoriesRepository

    override suspend fun getOrdersList(): Flow<List<ProductData>> {
        return orderRepository.getProductsRealTime()
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
            getProductsRealTime()
                .collect(){
                    mergedList.addAll(it)
                }
        }

        emit(mergedList)

    }


}