package uz.gita.core.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
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
import uz.gita.core.repository.internal.OrderRepository

const val ORDER_FOLDER = "orders"

internal class OrderRepositoryImpl : OrderRepository {

    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser!!
    private val userCollection = db.collection(USER_FOLDER)
    private val orders = userCollection.document(user.uid).collection(ORDER_FOLDER)

    override fun placingOrder(orderData: OrderData): Flow<Result<Unit>> = callbackFlow{

        orders.document(orderData.id).set(orderData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
                Log.d("TTT", "DocumentSnapshot successfully written!")
            }

        awaitClose{}
    }

    override fun getOrders(): Flow<Result<List<OrderData>>> = channelFlow{

        orders.get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    trySend(Result.success(it.documents.map { item -> Mapper.run { item.toOrderData() } }))
                }
            }

    }

    override fun getOrdersRealTime(): Flow<List<OrderData>> = callbackFlow{

        val list = orders

        val what = list.addSnapshotListener { value, _ ->
            if (value != null) {
                val orderList = value.map { it.toOrderData() }
                trySend(orderList)
            }
        }
        awaitClose { what.remove() }
    }
}