package uz.gita.fastfooddelivery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfooddelivery.directions.ProfileDirections
import uz.gita.fastfooddelivery.view.profile.viewmodel.ProfileIntent
import uz.gita.fastfooddelivery.view.profile.viewmodel.ProfileUiState
import uz.gita.fastfooddelivery.view.profile.viewmodel.ProfileViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val directions: ProfileDirections
) : ProfileViewModel, ViewModel() {

    private val authRep = StoreRepository.storeAuthRepository
    private val profileRep = StoreRepository.storeProfileRepository

    override val container: Container<ProfileUiState, Nothing> = container(ProfileUiState()) {
        fetchUserInfo()
    }

    override fun onEventDispatcher(intent: ProfileIntent) {
        viewModelScope.launch {
            when (intent) {
                is ProfileIntent.SignOut -> signOut()
            }
        }
    }

    private suspend fun signOut() {
        authRep.signOut()
        directions.navigateToLogin()
    }

    private fun fetchUserInfo() = intent {
        viewModelScope.launch {
            profileRep.getUserInfo().collectLatest {
                it.onSuccess {
                    reduce {
                        state.copy(
                            marketName = it.name,
                            marketLocation = it.location,
                            marketImgUrl = it.imgUrl
                        )
                    }
                }

            }

        }
    }

}