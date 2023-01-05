package uz.gita.fastfoodorder.view.profile.viewmodel

import uz.gita.core.data.models.ClientData
import uz.gita.fastfoodorder.AppViewModel

interface ProfileViewModel: AppViewModel<ProfileIntent, ProfileUiState, Nothing> {
}

sealed interface ProfileIntent {
    object SignOut:ProfileIntent
}

data class ProfileUiState (
    val clientData: ClientData = ClientData(),
)
