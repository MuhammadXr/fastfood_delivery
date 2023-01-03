package uz.gita.fastfooddelivery.view.profile.viewmodel

import uz.gita.fastfooddelivery.AppViewModel

interface ProfileViewModel: AppViewModel<ProfileIntent, ProfileUiState, Nothing> {
}

sealed interface ProfileIntent {
    object SignOut:ProfileIntent
}

data class ProfileUiState (
    val marketName: String = "Empty",
    val marketLocation: String = "Empty",
    val marketImgUrl: String = ""
)
