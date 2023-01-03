package uz.gita.core.repository.impl

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.core.repository.internal.FilesRepository
import java.io.File

internal class FilesRepositoryImpl : FilesRepository {

    private val fileReference = Firebase.storage.reference
    private val user = Firebase.auth.currentUser!!
    private val userFolder = fileReference.child(user.uid)

    private val imagesFolder = "images"


    override fun uploadFile(uri: Uri, fileName: String, fileDirectory: String): Flow<Result<Uri?>> =
        callbackFlow {

            val file = Uri.fromFile(File(uri.path.toString()))

            userFolder.child("$fileDirectory/$fileName")
            val uploadTask = userFolder.putFile(file)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl
                        .addOnSuccessListener {
                            trySend(Result.success(it))
                        }
                }.addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose {}
        }

    override fun uploadImage(uri: Uri, fileName: String): Flow<Result<Uri?>> {
        return uploadFile(uri, fileName, imagesFolder)
    }

    override fun deleteFile(fileName: String, fileDirectory: String): Flow<Result<Unit>> =
        callbackFlow {
            userFolder.child("$fileDirectory/$fileName").delete()
                .addOnSuccessListener {
                    trySend(Result.success(Unit))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }

            awaitClose {}
        }
}