package uz.gita.fastfooddelivery.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.ProductData
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfooddelivery.domain.usecases.AddProductUseCase
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(): AddProductUseCase {

    private val categoryRep = StoreRepository.categoriesRepository
    private val orderRep = StoreRepository.productsRepository

    override fun getCategories(): Flow<List<CategoryData>> = categoryRep.getCategories()

    override fun addOrder(productData: ProductData): Flow<Result<Unit>> = orderRep.placingProducts(productData)
}