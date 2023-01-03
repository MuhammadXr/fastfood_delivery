package uz.gita.core.repository.impl

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
import uz.gita.core.data.Mapper.toUserData
import uz.gita.core.data.models.UserData
import uz.gita.core.repository.internal.AuthRepository
import java.util.concurrent.TimeUnit

class AuthRepositoryImpl : AuthRepository {

    private val auth = Firebase.auth
    private var verificationID: String = "";

    override val signInState = MutableStateFlow(Result.success<UserData?>(null))

    override val codeInvalidState = MutableStateFlow(Unit)
    override val isSignedIn: Boolean
        get() = auth.currentUser != null

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
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        auth.updateCurrentUser(user)

                        val fireStore = Firebase.firestore
                        val checkRegister = fireStore.collection("users").document(user.uid)
                        checkRegister.get().addOnCompleteListener { value ->
                            val userData = value.result.toUserData()
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
        val options = PhoneAuthOptions.newBuilder(auth)
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

    override fun signOut() = auth.signOut()

}

