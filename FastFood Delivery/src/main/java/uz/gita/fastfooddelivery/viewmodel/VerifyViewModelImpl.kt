package uz.gita.fastfooddelivery.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.DeliveryRepository
import uz.gita.fastfooddelivery.directions.VerifyDirections
import uz.gita.fastfooddelivery.utils.MyToast
import uz.gita.fastfooddelivery.view.auth_ui.verification.viewmodel.VerifyIntent
import uz.gita.fastfooddelivery.view.auth_ui.verification.viewmodel.VerifyUiState
import uz.gita.fastfooddelivery.view.auth_ui.verification.viewmodel.VerifyViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(
    val directions: VerifyDirections,
    private val toast: MyToast
) : VerifyViewModel, ViewModel() {

    private val auth = DeliveryRepository.authRepository
    val uiState = VerifyUiState()

    override val container: Container<VerifyUiState, Nothing> = container(uiState) {
        init()
    }

    override fun enterPhoneNumber(phoneNumber: String, activity: Activity) {
        auth.enterPhone(phoneNumber, activity)
    }

    override fun onEventDispatcher(intent: VerifyIntent) {
        viewModelScope.launch {
            when (intent) {
                is VerifyIntent.VerifyCode -> auth.verifyCode(intent.code)
                else -> {}
            }
        }
    }

    private fun init(): Unit = intent {
        viewModelScope.launch {
            launch {
                auth.codeInvalidState.collectLatest {
                    toast.makeText("Kod Xato!")
                }
            }
            launch {
                auth.signInState.collectLatest { result ->
                    result
                        .onSuccess {
                            it?.let {
                                if (it.signed.not())
                                    directions.navigateToRegister()
                                else
                                    directions.navigateToMainTab()
                            }

                        }
                }
            }
        }
    }
}