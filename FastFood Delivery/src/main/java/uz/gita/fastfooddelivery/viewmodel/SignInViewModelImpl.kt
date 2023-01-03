package uz.gita.fastfooddelivery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.fastfooddelivery.directions.SignInDirections
import uz.gita.fastfooddelivery.view.auth_ui.login.viewmodel.SignInIntent
import uz.gita.fastfooddelivery.view.auth_ui.login.viewmodel.SignInUiState
import uz.gita.fastfooddelivery.view.auth_ui.login.viewmodel.SignInViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    val directions: SignInDirections
): SignInViewModel, ViewModel() {


    val uiState = SignInUiState()

    override val container: Container<SignInUiState, Nothing> = container(uiState)

    override fun onEventDispatcher(intent: SignInIntent) {
        viewModelScope.launch {
            when(intent){
                is SignInIntent.SignIn -> directions.navigateToVerify(intent.phoneNumber)
                else -> {}
            }
        }

    }

}