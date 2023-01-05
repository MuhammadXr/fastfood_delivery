package uz.gita.fastfoodorder.view.auth_ui.registration.viewmodel

import android.net.Uri
import uz.gita.fastfoodorder.AppViewModel

interface RegisterViewModel : AppViewModel<RegisterIntent, RegisterUiState, Nothing> {
}

sealed interface RegisterIntent {
    class RegisterClient(
        val clientName: String,
        val clientLocation: String,
        val clientContact: String,
        val imgUri: Uri
    ) : RegisterIntent
}

data class RegisterUiState(
    val state: Boolean = true
)
