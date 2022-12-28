package uz.gita.core.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface FilesRepository {
    fun uploadFile(uri: Uri, fileName: String, fileDirectory:String): Flow<Result<Uri?>>
    fun uploadImage(uri: Uri, fileName: String): Flow<Result<Uri?>>
    fun deleteFile(fileName: String, fileDirectory: String): Flow<Result<Unit>>
}