package uz.gita.core.repository.internal.store.impl

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper.toCategoryData
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.store.CategoriesRepository



internal class CategoriesRepositoryImpl : CategoriesRepository {


    override fun addCategory(categoryData: CategoryData): Flow<Result<Unit>> = channelFlow {

        FireBaseFields.storeCategories.document().set(categoryData)
            .addOnCompleteListener {
                trySend(Result.success(Unit))
            }

    }.flowOn(Dispatchers.IO)

    override fun updateCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {

        FireBaseFields.storeCategories.document(categoryData.id).update(
            "id", categoryData.id,
            "name", categoryData.name)
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun deleteCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {

        FireBaseFields.storeCategories.document(categoryData.id).delete()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun getCategories(): Flow<List<CategoryData>> = callbackFlow{

        val list =  FireBaseFields.storeCategories
        val listener = list.addSnapshotListener{value, _ ->
            if (value != null){
                val categoryList = value.documents.map { it.toCategoryData() }
                trySend(categoryList)
            }
        }

        awaitClose { listener.remove() }
    }.flowOn(Dispatchers.IO)
}