package uz.gita.core.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.CategoriesRepository

internal class CategoriesRepositoryImpl : CategoriesRepository {

    private val db = Firebase.firestore

    override fun addCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {
        db.collection("categories").document().set(categoryData)
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun updateCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {
        db.collection("categories").document(categoryData.id).update(
            "id", categoryData.id,
            "name", categoryData.name)
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun deleteCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {
        db.collection("categories").document(categoryData.id).delete()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun getCategories(): Flow<Result<List<CategoryData>>> = flow {
        db.collection("categories").get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val list = it.documents.map { item -> Mapper.run { item.toCategoryData() } }
                    emit(Result.success(list))
                }
            }
            .addOnFailureListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.failure(it))
                }
            }
    }
}