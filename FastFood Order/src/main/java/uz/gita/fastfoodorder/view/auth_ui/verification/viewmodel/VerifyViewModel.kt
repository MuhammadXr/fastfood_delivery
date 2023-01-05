package uz.gita.fastfoodorder.view.auth_ui.verification.viewmodel

import android.app.Activity
import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.auth.FirebaseUser
import uz.gita.fastfoodorder.AppViewModel

interface VerifyViewModel : AppViewModel<VerifyIntent, VerifyUiState, Nothing>{
    fun enterPhoneNumber(phoneNumber: String, activity: Activity)
}

sealed interface VerifyIntent {
    class VerifyCode(val code: String): VerifyIntent
}

data class VerifyUiState(
    val user: FirebaseUser? = null
)
