package uz.gita.fastfoodorder.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.data.models.ClientData
import uz.gita.core.repository.ClientRepository
import uz.gita.fastfoodorder.directions.RegisterDirections
import uz.gita.fastfoodorder.view.auth_ui.registration.viewmodel.RegisterIntent
import uz.gita.fastfoodorder.view.auth_ui.registration.viewmodel.RegisterUiState
import uz.gita.fastfoodorder.view.auth_ui.registration.viewmodel.RegisterViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val directions: RegisterDirections
) : RegisterViewModel, ViewModel() {

    private val profileRep = ClientRepository.clientProfileRepository
    private val filesRep = ClientRepository.filesRepository

    override val container: Container<RegisterUiState, Nothing> = container(RegisterUiState())

    override fun onEventDispatcher(intent: RegisterIntent) {
        viewModelScope.launch {
            when (intent) {
                is RegisterIntent.RegisterClient -> registerMarket(
                    name = intent.clientName,
                    location = intent.clientLocation,
                    contact = intent.clientContact,
                    imgUri = intent.imgUri
                )
            }
        }
    }

    private suspend fun registerMarket(name: String, location: String, contact: String, imgUri: Uri) {

        val clientData = ClientData(
            name = name,
            location = location,
            contact = contact
        )

        filesRep.uploadImage(imgUri, name+location).collectLatest { result ->
            result
                .onSuccess {
                    it?.let { uri ->
                        clientData.imgUrl = uri.toString()
                        profileRep.register(
                            clientData = clientData
                        ).collect {
                            it
                                .onSuccess {
                                    directions.navigateToMainTab()
                                }
                                .onFailure {

                                }
                        }
                    }
                }

        }


    }

}