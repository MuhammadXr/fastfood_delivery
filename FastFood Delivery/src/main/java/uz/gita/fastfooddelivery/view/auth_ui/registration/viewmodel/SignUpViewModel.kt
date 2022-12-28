package uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel

import uz.gita.fastfooddelivery.AppViewModel

interface SignUpViewModel: AppViewModel<SignUpIntent, SignUpUiState, Nothing>

sealed interface SignUpIntent {

}

data class SignUpUiState (
    val state: Boolean = true
)
