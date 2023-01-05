package uz.gita.fastfoodorder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.ClientRepository
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfoodorder.directions.ProfileDirections
import uz.gita.fastfoodorder.view.profile.viewmodel.ProfileIntent
import uz.gita.fastfoodorder.view.profile.viewmodel.ProfileUiState
import uz.gita.fastfoodorder.view.profile.viewmodel.ProfileViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val directions: ProfileDirections
) : ProfileViewModel, ViewModel() {

    private val authRep = ClientRepository.clientAuthRepository
    private val profileRep = ClientRepository.clientProfileRepository

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
                            clientData = it
                        )
                    }
                }

            }

        }
    }

}