package uz.gita.core.repository.internal.client.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.Mapper.toProductData
import uz.gita.core.data.models.OrderData
import uz.gita.core.data.models.ProductData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.client.ClientOrdersRepository


internal class ClientOrdersRepositoryImpl : ClientOrdersRepository {


    override fun placingOrder(orderData: OrderData): Flow<Result<Unit>> = callbackFlow{
        orderData.clientId = FireBaseFields.user.uid
        FireBaseFields.clientOrders.document().set(orderData)
            .addOnSuccessListener { trySend(Result.success(Unit)) }
            .addOnFailureListener { trySend(Result.failure(it)) }

        awaitClose {  }
    }

    override fun getAllProducts(): Flow<List<ProductData>> = callbackFlow{


        val listener = FireBaseFields.clientOrders.addSnapshotListener{ result, _ ->
            result?.let { value ->
                val list = value.documents.map { it.toProductData() }

                trySend(list)

            }
        }

        awaitClose { listener.remove() }
    }
}