package uz.gita.fastfooddelivery.view.auth_ui.registration

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.fastfooddelivery.screen_items.ImageSelectItem
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.RegisterIntent
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.RegisterUiState
import uz.gita.fastfooddelivery.view.auth_ui.registration.viewmodel.RegisterViewModel
import uz.gita.fastfooddelivery.viewmodel.RegisterViewModelImpl

class RegistrationScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: RegisterViewModel = getViewModel<RegisterViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        RegistrationScreenContent(
            uiState = uiState,
            eventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun RegistrationScreenContent(
    uiState: RegisterUiState,
    eventDispatcher: (RegisterIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val marketName = remember {
            mutableStateOf("")
        }
        val marketLocation = remember {
            mutableStateOf("")
        }
        val imageUri = remember {
            mutableStateOf(Uri.EMPTY)
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Please Register Your Market!")
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column {
            val activity = LocalContext.current as Activity
            ImageSelectItem(imageUri = imageUri, activity = activity)
            Spacer(modifier = Modifier.size(4.dp))
            MarketNameField(
                mutableState = marketName,
                onValueChange = { marketName.value = it }
            ) {
                Text(text = "Market Name")
            }
        }

        Spacer(modifier = Modifier.size(4.dp))

        MarketLocationField(
            mutableState = marketLocation, onValueChange = { marketLocation.value = it }
        ) {
            Text(text = "Market Location")
        }

        Button(onClick = {
            eventDispatcher.invoke(
                RegisterIntent.RegisterMarket(
                    marketName = marketName.value,
                    marketLocation = marketLocation.value,
                    imgUri = imageUri.value
                )
            )
        }) {
            Text(text = "Register!")
        }

    }
}

@Composable
fun MarketNameField(
    mutableState: MutableState<String>,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?
) {
    TextField(
        value = mutableState.value,
        onValueChange = onValueChange,
        placeholder = placeholder
    )
}

@Composable
fun MarketLocationField(
    mutableState: MutableState<String>,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?
) {
    TextField(
        value = mutableState.value,
        onValueChange = onValueChange,
        placeholder = placeholder
    )
}