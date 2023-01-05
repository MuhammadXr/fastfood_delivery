package uz.gita.core.repository.internal.store

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.DeliveryData

interface DeliveryRepository {
    fun placingDelivery(deliveryData: DeliveryData) : Flow<Result<Unit>>
    fun getDeliveryData() : Flow<Result<List<DeliveryData>>>
    fun getDeliveryDataRealTime(): Flow<List<DeliveryData>>
}