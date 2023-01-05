package uz.gita.core.repository.internal.store

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.StoreData

interface StoreProfileRepository {
    fun register(storeData: StoreData): Flow<Result<Unit>>
    fun getUserInfo(): Flow<Result<StoreData>>
}