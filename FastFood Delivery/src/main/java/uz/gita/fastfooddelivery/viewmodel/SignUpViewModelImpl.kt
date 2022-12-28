package uz.gita.fastfooddelivery.viewmodel

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.SignUpIntent
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.SignUpUiState
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.SignUpViewModel

class SignUpViewModelImpl : SignUpViewModel, ViewModel() {

    val uiState = SignUpUiState()

    override val container: Container<SignUpUiState, Nothing> = container(uiState)

    override fun onEventDispatcher(intent: SignUpIntent) {

    }

}