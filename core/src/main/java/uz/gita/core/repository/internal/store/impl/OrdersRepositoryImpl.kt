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
import uz.gita.core.data.Mapper.toOrderData
import uz.gita.core.data.models.OrderData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.store.OrdersRepository



internal class OrdersRepositoryImpl : OrdersRepository {

    override fun placingOrder(orderData: OrderData): Flow<Result<Unit>> = callbackFlow{

        FireBaseFields.storeOrders.document(orderData.id).set(orderData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
                Log.d("TTT", "DocumentSnapshot successfully written!")
            }

        awaitClose{}
    }

    override fun getOrders(): Flow<Result<List<OrderData>>> = channelFlow{

        FireBaseFields.storeOrders.get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    trySend(Result.success(it.documents.map { item -> Mapper.run { item.toOrderData() } }))
                }
            }

    }

    override fun getOrdersRealTime(): Flow<List<OrderData>> = callbackFlow{

        val list = FireBaseFields.storeOrders

        val what = list.addSnapshotListener { value, _ ->
            if (value != null) {
                val orderList = value.map { it.toOrderData() }
                trySend(orderList)
            }
        }
        awaitClose { what.remove() }
    }
}