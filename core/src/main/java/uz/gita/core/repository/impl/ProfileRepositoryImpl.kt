package uz.gita.core.repository.impl

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.data.Mapper.toUserData
import uz.gita.core.data.models.UserData
import uz.gita.core.repository.internal.ProfileRepository

const val USER_FOLDER = "users"

internal class ProfileRepositoryImpl : ProfileRepository {

    private val user = Firebase.auth.currentUser!!
    private val db = Firebase.firestore
    private val userCollection = db.collection(USER_FOLDER)


    override fun register(name: String, location: String, imgUrl: String): Flow<Result<Unit>> =
        callbackFlow {
            val userData = UserData(
                id = user.uid,
                name = name,
                location = location,
                imgUrl = imgUrl,
                signed = true
            )
            userCollection.document(user.uid).set(userData)
                .addOnSuccessListener {
                    trySend(Result.success(Unit))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose { }
        }

    override fun getUserInfo(): Flow<Result<UserData>> = callbackFlow {
        userCollection.document(user.uid).get()
            .addOnSuccessListener { result ->
                trySend(Result.success(result.toUserData()))
            }.addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose {  }
    }

}