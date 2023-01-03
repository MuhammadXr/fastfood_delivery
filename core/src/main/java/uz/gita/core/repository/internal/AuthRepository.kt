package uz.gita.core.repository.internal

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.core.data.models.UserData

interface AuthRepository {

    val signInState: StateFlow<Result<UserData?>>
    val codeInvalidState: StateFlow<Unit>
    val isSignedIn: Boolean

    fun enterPhone( phoneNumber: String, activity: Activity)
    fun verifyCode(code: String)
    fun signOut()
}