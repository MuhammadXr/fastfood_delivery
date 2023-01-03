package uz.gita.fastfooddelivery.view.splash.viewmodel

import uz.gita.fastfooddelivery.AppViewModel

interface SplashViewModel : AppViewModel<SplashIntent, SplashUiState, Nothing> {

}

sealed interface SplashIntent {

}

data class SplashUiState(
    val state: Boolean = false
)
