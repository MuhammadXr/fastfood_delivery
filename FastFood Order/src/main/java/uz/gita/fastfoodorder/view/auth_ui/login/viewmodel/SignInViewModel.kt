package uz.gita.fastfoodorder.view.auth_ui.login.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.fastfoodorder.AppViewModel

interface SignInViewModel : AppViewModel<SignInIntent, SignInUiState, Nothing> {

}

sealed interface SignInIntent {
    class SignIn(val phoneNumber: String ): SignInIntent
}

data class SignInUiState(
    val state: Boolean = true
)

