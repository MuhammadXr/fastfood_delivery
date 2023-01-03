package uz.gita.fastfooddelivery.view.auth_ui.verification

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.fastfooddelivery.view.auth_ui.verification.viewmodel.VerifyIntent
import uz.gita.fastfooddelivery.view.auth_ui.verification.viewmodel.VerifyUiState
import uz.gita.fastfooddelivery.view.auth_ui.verification.viewmodel.VerifyViewModel
import uz.gita.fastfooddelivery.viewmodel.VerifyViewModelImpl

class VerificationScreen (private val phoneNumber: String): AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: VerifyViewModel = getViewModel<VerifyViewModelImpl>()
        viewModel.enterPhoneNumber(phoneNumber, LocalContext.current as Activity)
        val uiState = viewModel.collectAsState().value

        VerificationScreenContent(uiState, viewModel::onEventDispatcher)
    }
}


@Composable
fun VerificationScreenContent(
    uiState: VerifyUiState,
    eventDispatcher: (VerifyIntent) -> Unit
) {

    val otpCode = remember {
        mutableStateOf("")
    }
    val checkButtonState = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OTPField(text = otpCode.value, onValueChange = { otpCode.value = it; checkButtonState.value = it.length == 6 }, otpCount = 6)
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                eventDispatcher.invoke(VerifyIntent.VerifyCode(otpCode.value))
            }
        ) {
            Text(text = "Check")
        }
    }
}


@Composable
fun OTPField(
    text: String,
    onValueChange: ((String) -> Unit),
    otpCount: Int
) {

    Row(
        modifier = Modifier
            .width(280.dp)
            .height(64.dp)
            .padding(10.dp)
    ) {
        BasicTextField(
            value = text, onValueChange = {
                if (it.length <= otpCount && it.isDigitsOnly()) {
                    onValueChange.invoke(it)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    repeat(otpCount) { index ->
                        Spacer(modifier = Modifier.width(4.dp))
                        CharView(index = index, text = text)
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
        )
    }
}

@Composable
fun CharView(
    index: Int,
    text: String
) {
    Column(
        modifier = Modifier
            .width(36.dp)
            .height(64.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val char = when {
            index >= text.length -> ""
            else -> text[index].toString()
        }

        Text(
            text = char,
            color = Color.Black,
            modifier = Modifier.wrapContentHeight(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}