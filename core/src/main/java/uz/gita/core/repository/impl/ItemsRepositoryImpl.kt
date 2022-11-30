package uz.gita.core.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper
import uz.gita.core.data.models.FoodData
import uz.gita.core.repository.ItemsRepository

internal class ItemsRepositoryImpl : ItemsRepository {

    private val db = Firebase.firestore

    override fun addItems(foodData: FoodData): Flow<Result<Unit>> = flow{
        db.collection("items").document(foodData.id).set(foodData)
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun deleteItems(foodData: FoodData): Flow<Result<Unit>> = flow{
        db.collection("items").document(foodData.id).delete()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun update(foodData: FoodData): Flow<Result<Unit>> = flow{
        db.collection("items").document(foodData.id).set(foodData)
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(Unit))
                }
            }
    }

    override fun getAllFoods(): Flow<Result<List<FoodData>>> = flow{
        db.collection("items").get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.success(it.documents.map { item -> Mapper.run { item.toFoodData() } }))
                }
            }
            .addOnFailureListener {
                CoroutineScope(Dispatchers.IO).launch {
                    emit(Result.failure(it))
                }
            }
    }
}