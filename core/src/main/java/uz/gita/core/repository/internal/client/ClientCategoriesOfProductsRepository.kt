package uz.gita.core.repository.internal.client

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData

interface ClientCategoriesOfProductsRepository {
    fun getCategories(): Flow<List<CategoryData>>
}