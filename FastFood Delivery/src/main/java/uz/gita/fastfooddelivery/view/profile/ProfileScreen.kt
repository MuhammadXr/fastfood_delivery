package uz.gita.fastfooddelivery.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.skydoves.landscapist.glide.GlideImage
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.fastfooddelivery.view.profile.viewmodel.ProfileIntent
import uz.gita.fastfooddelivery.view.profile.viewmodel.ProfileUiState
import uz.gita.fastfooddelivery.view.profile.viewmodel.ProfileViewModel
import uz.gita.fastfooddelivery.viewmodel.ProfileViewModelImpl

class ProfileScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: ProfileViewModel = getViewModel<ProfileViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        ProfileScreenContent(
            uiState = uiState,
            eventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun ProfileScreenContent(
    uiState: ProfileUiState,
    eventDispatcher: (ProfileIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(imageModel = { uiState.marketImgUrl },
            modifier = Modifier
                .size(200.dp)
                .background(color = Color.White, RoundedCornerShape(20.dp))
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Market Name: ")
            Text(text = uiState.marketName)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Market Location: ")
            Text(text = uiState.marketLocation)
        }

        Button(onClick = { eventDispatcher.invoke(ProfileIntent.SignOut) }) {
            Text(text = "Sign out")
        }
    }
}