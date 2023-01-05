package uz.gita.core.repository.internal.store.impl

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import uz.gita.core.data.Mapper.toStoreData
import uz.gita.core.data.models.StoreData
import uz.gita.core.repository.FireBaseFields
import uz.gita.core.repository.internal.store.StoreAuthRepository
import java.util.concurrent.TimeUnit

internal class StoreAuthRepositoryImpl : StoreAuthRepository {


    private var verificationID: String = "";

    override val signInState = MutableStateFlow(Result.success<StoreData?>(null))

    override val codeInvalidState = MutableStateFlow(Unit)
    override val isSignedIn: Boolean
        get() = FireBaseFields.auth.currentUser != null

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TTT", "INVALID REQUEST")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            verificationID = verificationId
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FireBaseFields.auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        FireBaseFields.auth.updateCurrentUser(user)


                        val checkRegister = FireBaseFields.storeData
                        checkRegister.get().addOnCompleteListener { value ->
                            val userData = value.result.toStoreData()
                            signInState.value = Result.success(userData)
                        }

                    }


                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        codeInvalidState.value = Unit
                        Log.d("TTT", "Xato kod")
                    }
                    // Update UI
                }
            }


    }


    override fun enterPhone(phoneNumber: String, activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(FireBaseFields.auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setCallbacks(callbacks)
            .setActivity(activity)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationID, code)
        signInWithPhoneAuthCredential(credential)
    }

    override fun signOut() = FireBaseFields.auth.signOut()

}

