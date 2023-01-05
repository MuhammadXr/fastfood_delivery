package uz.gita.core.repository.internal.store.impl

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper
import uz.gita.core.data.Mapper.toDeliveryData
import uz.gita.core.data.models.DeliveryData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.store.DeliveryRepository



internal class DeliveryRepositoryImpl : DeliveryRepository {


    override fun placingDelivery(deliveryData: DeliveryData): Flow<Result<Unit>> = callbackFlow {
        FireBaseFields.storeDelivery.document(deliveryData.id).set(deliveryData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
                Log.d("TTT", "DocumentSnapshot successfully written!")
            }

        awaitClose{}
    }

    override fun getDeliveryData(): Flow<Result<List<DeliveryData>>> = channelFlow {
        FireBaseFields.storeDelivery.get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    trySend(Result.success(it.documents.map { item -> Mapper.run { item.toDeliveryData() } }))
                }
            }
    }

    override fun getDeliveryDataRealTime(): Flow<List<DeliveryData>> = callbackFlow{
        val list = FireBaseFields.storeDelivery

        val what = list.addSnapshotListener { value, _ ->
            if (value != null) {
                val deliveryList = value.map { it.toDeliveryData() }
                trySend(deliveryList)
            }
        }
        awaitClose { what.remove() }
    }
}