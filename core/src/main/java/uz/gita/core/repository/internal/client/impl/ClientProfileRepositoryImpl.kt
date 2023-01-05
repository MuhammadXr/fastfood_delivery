package uz.gita.core.repository.internal.client.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.Mapper.toClientData
import uz.gita.core.data.models.ClientData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.client.ClientProfileRepository

internal class ClientProfileRepositoryImpl : ClientProfileRepository {


    override fun register(clientData: ClientData): Flow<Result<Unit>> =  callbackFlow {

        FireBaseFields.clientData.set(clientData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose { }
    }

    override fun getUserInfo(): Flow<Result<ClientData>> = callbackFlow {
        FireBaseFields.clientData.get()
            .addOnSuccessListener { result ->
                trySend(Result.success(result.toClientData()))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose {  }
    }
}