package uz.gita.core.repository.impl

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper
import uz.gita.core.data.Mapper.toCategoryData
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.CategoriesRepository

internal class CategoriesRepositoryImpl : CategoriesRepository {

    private val db = Firebase.firestore

    override fun addCategory(categoryData: CategoryData): Flow<Result<Unit>> = channelFlow {
        db.collection("categories").document().set(categoryData)
            .addOnCompleteListener {
                trySend(Result.success(Unit))
            }

    }.flowOn(Dispatchers.IO)

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

    override fun getCategories(): Flow<List<CategoryData>> = callbackFlow{
        val list = db.collection("categories")
//            .addOnCompleteListener {
//                    val list = it.result.documents.map { item -> Mapper.run { item.toCategoryData() } }
//                Log.d("TTT", "LIST SIZE ${list.size}")
//                    trySend(list)
//
//            }
        val listener = list.addSnapshotListener{value, _ ->
            if (value != null){
                val categoryList = value.documents.map { it.toCategoryData() }
                Log.d("TTT", "CATEGORIYA olindi ${categoryList.size}")
                trySend(categoryList)
            }
        }

        awaitClose { listener.remove() }
    }.flowOn(Dispatchers.IO)
}