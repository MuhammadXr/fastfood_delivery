package uz.gita.core.repository.internal.store.impl

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.core.data.Mapper
import uz.gita.core.data.Mapper.toProductData
import uz.gita.core.data.models.ProductData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.store.ProductsRepository



internal class ProductsRepositoryImpl : ProductsRepository {

    override fun placingProducts(productData: ProductData): Flow<Result<Unit>> = callbackFlow{

        FireBaseFields.storeProducts.document(productData.id).set(productData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
                Log.d("TTT", "DocumentSnapshot successfully written!")
            }

        awaitClose{}
    }

    override fun getProducts(): Flow<Result<List<ProductData>>> = channelFlow{

        FireBaseFields.storeProducts.get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    trySend(Result.success(it.documents.map { item -> Mapper.run { item.toProductData() } }))
                }
            }

    }

    override fun getProductsRealTime(): Flow<List<ProductData>> = callbackFlow{

        val list = FireBaseFields.storeProducts

        val what = list.addSnapshotListener { value, _ ->
            if (value != null) {
                val productList = value.map { it.toProductData() }
                trySend(productList)
            }
        }
        awaitClose { what.remove() }
    }
}