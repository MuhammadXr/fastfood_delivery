package uz.gita.core.repository.internal

import kotlinx.coroutines.flow.Flow
import uz.gita.core.data.models.UserData

interface ProfileRepository {
    fun register(name:String, location:String, imgUrl: String): Flow<Result<Unit>>
    fun getUserInfo(): Flow<Result<UserData>>
}