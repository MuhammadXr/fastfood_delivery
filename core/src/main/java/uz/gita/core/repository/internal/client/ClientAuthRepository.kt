package uz.gita.core.repository.internal.client

import android.app.Activity
import kotlinx.coroutines.flow.StateFlow
import uz.gita.core.data.models.StoreData

interface ClientAuthRepository {
    val signInState: StateFlow<Result<StoreData?>>
    val codeInvalidState: StateFlow<Unit>
    val isSignedIn: Boolean

    fun enterPhone( phoneNumber: String, activity: Activity)
    fun verifyCode(code: String)
    fun signOut()
}