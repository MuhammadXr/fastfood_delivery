package uz.gita.fastfooddelivery.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.core.repository.StoreRepository
import uz.gita.fastfooddelivery.directions.RegisterDirections
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.RegisterIntent
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.RegisterUiState
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.RegisterViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val directions: RegisterDirections
) : RegisterViewModel, ViewModel() {

    private val profileRep = StoreRepository.storeProfileRepository
    private val filesRep = StoreRepository.filesRepository

    override val container: Container<RegisterUiState, Nothing> = container(RegisterUiState())

    override fun onEventDispatcher(intent: RegisterIntent) {
        viewModelScope.launch {
            when (intent) {
                is RegisterIntent.RegisterMarket -> registerMarket(
                    marketName = intent.marketName,
                    marketLoc = intent.marketLocation,
                    imgUri = intent.imgUri
                )
            }
        }
    }

    private suspend fun registerMarket(marketName: String, marketLoc: String, imgUri: Uri) {

        filesRep.uploadImage(imgUri, marketName+marketLoc).collectLatest {
            it
                .onSuccess {
                    it?.let {
                        profileRep.register(
                            name = marketName,
                            location = marketLoc,
                            imgUrl = it.toString()
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