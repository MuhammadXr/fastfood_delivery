package uz.gita.core.repository.internal.client.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.Mapper.toClientData
import uz.gita.core.data.Mapper.toStoreData
import uz.gita.core.data.models.StoreData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.client.ClientStoresRepository


internal class ClientStoresRepositoryImpl : ClientStoresRepository {


    override fun getAllStores(): Flow<Result<List<StoreData>>> = callbackFlow {
        val listener = FireBaseFields.storeCollection.addSnapshotListener{ value, _ ->
            value?.let { document ->
                val storesList = document.documents.map { it.toStoreData() }
                trySend(Result.success(storesList))
            }
        }

        awaitClose{ listener.remove() }
    }

    override fun selectStore(storeData: StoreData): Flow<Result<Unit>> = callbackFlow{
        val userData = FireBaseFields.clientData.get().result.toClientData()
        userData.selectedStoreId = storeData.id

        FireBaseFields.clientData.set(userData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose{  }
    }
}