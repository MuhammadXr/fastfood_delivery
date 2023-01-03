package uz.gita.fastfooddelivery.view.auth_ui.login.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfooddelivery.AppViewModel

interface SignInViewModel : AppViewModel<SignInIntent, SignInUiState, Nothing> {

}

sealed interface SignInIntent {
    class SignIn(val phoneNumber: String ): SignInIntent
}

data class SignInUiState(
    val state: Boolean = true
)

