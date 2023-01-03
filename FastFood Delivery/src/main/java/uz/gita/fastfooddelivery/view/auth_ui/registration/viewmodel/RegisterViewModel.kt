package uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel

import android.net.Uri
import uz.gita.fastfooddelivery.AppViewModel

interface RegisterViewModel: AppViewModel<RegisterIntent, RegisterUiState, Nothing> {
}

sealed interface RegisterIntent {
    class RegisterMarket(val marketName: String, val marketLocation: String, val imgUri: Uri): RegisterIntent
}

data class RegisterUiState (
    val state: Boolean = true
)
