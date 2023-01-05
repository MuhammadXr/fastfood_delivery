package uz.gita.core.repository.internal.client

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.StoreData

interface ClientStoresRepository {
    fun getAllStores(): Flow<Result<List<StoreData>>>
    fun selectStore(storeData: StoreData): Flow<Result<Unit>>
}