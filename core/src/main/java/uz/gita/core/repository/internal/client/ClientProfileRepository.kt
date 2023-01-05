package uz.gita.core.repository.internal.client

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.ClientData
import uz.gita.core.data.models.StoreData

interface ClientProfileRepository {
    fun register(clientData: ClientData): Flow<Result<Unit>>
    fun getUserInfo(): Flow<Result<ClientData>>
}