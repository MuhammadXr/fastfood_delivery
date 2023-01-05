package uz.gita.core.repository.internal.store.impl

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.Mapper.toStoreData
import uz.gita.core.data.models.StoreData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.store.StoreProfileRepository



internal class StoreProfileRepositoryImpl : StoreProfileRepository {


    override fun register(storeData: StoreData): Flow<Result<Unit>> =
        callbackFlow {

            FireBaseFields.storeData.set(storeData)
                .addOnSuccessListener {
                    trySend(Result.success(Unit))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose { }
        }

    override fun getUserInfo(): Flow<Result<StoreData>> = callbackFlow {
        FireBaseFields.storeData.get()
            .addOnSuccessListener { result ->
                trySend(Result.success(result.toStoreData()))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose {  }
    }

}