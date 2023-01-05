package uz.gita.core.repository.internal.client.impl

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.models.ProductData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.client.ClientCartRepository


class ClientCartRepositoryImpl : ClientCartRepository {

    override fun addToCart(productData: ProductData): Flow<Result<Unit>> = callbackFlow{
        FireBaseFields.cartCollection.document().set(productData)
            .addOnSuccessListener { trySend(Result.success(Unit)) }
            .addOnFailureListener { trySend(Result.failure(it)) }

        awaitClose {  }
    }

    override fun removeFromCart(productData: ProductData): Flow<Result<Unit>> = callbackFlow{
        FireBaseFields.cartCollection.document(productData.id).delete()
            .addOnSuccessListener { trySend(Result.success(Unit)) }
            .addOnFailureListener { trySend(Result.failure(it)) }

        awaitClose {  }
    }
}