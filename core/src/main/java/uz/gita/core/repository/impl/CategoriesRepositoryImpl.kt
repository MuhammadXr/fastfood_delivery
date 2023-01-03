package uz.gita.core.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper.toCategoryData
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.internal.CategoriesRepository

const val CATEGORIES_FOLDER = "categories"

internal class CategoriesRepositoryImpl : CategoriesRepository {

    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser!!
    private val userCollection = db.collection(USER_FOLDER)
    private val categories = userCollection.document(user.uid).collection(CATEGORIES_FOLDER)


    override fun addCategory(categoryData: CategoryData): Flow<Result<Unit>> = channelFlow {

        categories.document().set(categoryData)
            .addOnCompleteListener {
                trySend(Result.success(Unit))
            }

    }.flowOn(Dispatchers.IO)

    override fun updateCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {

        categories.document(categoryData.id).update(
            "id", categoryData.id,
            "name", categoryData.name)
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun deleteCategory(categoryData: CategoryData): Flow<Result<Unit>> = flow {

        categories.document(categoryData.id).delete()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun getCategories(): Flow<List<CategoryData>> = callbackFlow{

        val list = categories
        val listener = list.addSnapshotListener{value, _ ->
            if (value != null){
                val categoryList = value.documents.map { it.toCategoryData() }
                trySend(categoryList)
            }
        }

        awaitClose { listener.remove() }
    }.flowOn(Dispatchers.IO)
}