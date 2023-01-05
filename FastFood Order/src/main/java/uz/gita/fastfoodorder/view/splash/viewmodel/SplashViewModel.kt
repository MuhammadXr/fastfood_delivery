package uz.gita.fastfoodorder.view.splash.viewmodel

import uz.gita.fastfoodorder.AppViewModel

interface SplashViewModel : AppViewModel<SplashIntent, SplashUiState, Nothing> {

}

sealed interface SplashIntent {

}

data class SplashUiState(
    val state: Boolean = false
)
