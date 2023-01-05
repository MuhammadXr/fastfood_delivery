package uz.gita.core.repository.internal.client.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.Mapper.toCategoryData
import uz.gita.core.data.models.CategoryData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.client.ClientCategoriesOfProductsRepository

class ClientCategoriesOfProductsRepositoryImpl : ClientCategoriesOfProductsRepository {


    override fun getCategories(): Flow<List<CategoryData>> = callbackFlow {

        val categories = FireBaseFields.clientCategories

        val listener = categories.addSnapshotListener{ value, _ ->
            value?.let { querySnapshot ->
                val list = querySnapshot.documents.map { it.toCategoryData() }
                trySend(list)
            }
        }

        awaitClose { listener.remove() }
    }
}