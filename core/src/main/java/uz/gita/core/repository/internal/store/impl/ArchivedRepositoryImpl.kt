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
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.data.Mapper
import uz.gita.core.data.Mapper.toArchivedOrderData
import uz.gita.core.data.models.ArchivedOrderData
import uz.gita.core.repository.internal.store.ArchivedRepository



internal class ArchivedRepositoryImpl : ArchivedRepository {

    override fun placingToArchived(archivedOrderData: ArchivedOrderData): Flow<Result<Unit>> = callbackFlow {
        FireBaseFields.archivedCollection.document(archivedOrderData.id).set(archivedOrderData)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
                Log.d("TTT", "DocumentSnapshot successfully written!")
            }

        awaitClose{}
    }

    override fun getArchivedData() : Flow<Result<List<ArchivedOrderData>>> = channelFlow {
        FireBaseFields.archivedCollection.get()
            .addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    trySend(Result.success(it.documents.map { item -> Mapper.run { item.toArchivedOrderData() } }))
                }
            }
    }

    override fun getArchivedDataRealTime(): Flow<List<ArchivedOrderData>> = callbackFlow{
        val list = FireBaseFields.archivedCollection

        val what = list.addSnapshotListener { value, _ ->
            if (value != null) {
                val archiveList = value.map { it.toArchivedOrderData() }
                trySend(archiveList)
            }
        }
        awaitClose { what.remove() }
    }
}