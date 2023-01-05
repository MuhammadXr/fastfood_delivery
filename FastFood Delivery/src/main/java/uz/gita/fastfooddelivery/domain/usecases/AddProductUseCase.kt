package uz.gita.fastfooddelivery.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData
import uz.gita.core.data.models.ProductData

interface AddProductUseCase {
    fun getCategories(): Flow<List<CategoryData>>
    fun addOrder(productData: ProductData): Flow<Result<Unit>>
}