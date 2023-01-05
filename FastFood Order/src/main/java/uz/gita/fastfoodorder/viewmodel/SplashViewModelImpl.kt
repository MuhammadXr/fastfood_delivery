package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfoodorder.directions.SplashDirections
import uz.gita.fastfoodorder.view.splash.viewmodel.SplashIntent
import uz.gita.fastfoodorder.view.splash.viewmodel.SplashUiState
import uz.gita.fastfoodorder.view.splash.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    val directions: SplashDirections
): SplashViewModel, ViewModel() {

    override val container: Container<SplashUiState, Nothing> = container(SplashUiState())
    private val authRep = StoreRepository.storeAuthRepository

    override fun onEventDispatcher(intent: SplashIntent) {

    }

    init {
        viewModelScope.launch {
            delay(1000)
            if (authRep.isSignedIn){
                directions.navigateToMainTab()
            }
            else {
                directions.navigateToLogin()
            }
        }
    }

}