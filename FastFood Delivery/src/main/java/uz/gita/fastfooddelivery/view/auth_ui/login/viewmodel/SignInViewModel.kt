package uz.gita.fastfooddelivery.view.auth_ui.login.viewmodel

import uz.gita.fastfooddelivery.AppViewModel

interface SignInViewModel : AppViewModel<SignInIntent, SignInUiState, Nothing>

sealed interface SignInUiState {

}

data class SignInIntent(
    val nothing: Nothing
)

