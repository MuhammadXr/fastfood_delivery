package uz.gita.core.repository.internal.store

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.ArchivedOrderData
import uz.gita.core.data.models.DeliveryData

interface ArchivedRepository {
    fun placingToArchived(archivedOrderData: ArchivedOrderData) : Flow<Result<Unit>>
    fun getArchivedData() : Flow<Result<List<ArchivedOrderData>>>
    fun getArchivedDataRealTime(): Flow<List<ArchivedOrderData>>
}