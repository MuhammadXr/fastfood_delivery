package uz.gita.core.repository.impl

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper
import uz.gita.core.data.Mapper.toOrderData
import uz.gita.core.data.models.OrderData
import uz.gita.core.repository.OrderRepository

internal class OrderRepositoryImpl : OrderRepository {

    private val db = Firebase.firestore

    override fun placingOrder(orderData: OrderData): Flow<Result<Unit>> = channelFlow{
        Log.d("TTT", "${orderData.id} ${orderData.name}")
        db.collection("orders").document(orderData.id).set(orderData)
            .addOnSuccessListener {
                Log.d("TTT", "DocumentSnapshot successfully written!")
            }
    }

    override fun getOrders(): Flow<Result<List<OrderData>>> = channelFlow{
        db.collection("orders").get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    trySend(Result.success(it.documents.map { item -> Mapper.run { item.toOrderData() } }))
                }
            }

    }

    override fun getOrdersRealTime(): Flow<List<OrderData>> = callbackFlow{
        val list = db.collection("orders")

        val what = list.addSnapshotListener { value, _ ->
            if (value != null) {
                val orderList = value.map { it.toOrderData() }
                trySend(orderList)
            }
        }
        awaitClose { what.remove() }
    }
}