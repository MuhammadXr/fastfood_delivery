package uz.gita.core.repository.internal

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.CategoryData

interface CategoriesRepository {
    fun addCategory(categoryData: CategoryData): Flow<Result<Unit>>
    fun updateCategory(categoryData: CategoryData): Flow<Result<Unit>>
    fun deleteCategory(categoryData: CategoryData): Flow<Result<Unit>>
    fun getCategories(): Flow<List<CategoryData>>
}