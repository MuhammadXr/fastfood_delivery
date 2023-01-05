package uz.gita.fastfoodorder.view.auth_ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.fastfoodorder.view.auth_ui.login.viewmodel.SignInIntent
import uz.gita.fastfoodorder.view.auth_ui.login.viewmodel.SignInUiState
import uz.gita.fastfoodorder.view.auth_ui.login.viewmodel.SignInViewModel
import uz.gita.fastfoodorder.viewmodel.SignInViewModelImpl


class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val viewModel :SignInViewModel= getViewModel<SignInViewModelImpl>()
        val uiState = viewModel.collectAsState().value

        SignInScreenContent(uiState, viewModel::onEventDispatcher)
    }

}

@Composable
fun SignInScreenContent(
    uiState: SignInUiState,
    evenDispatcher: (SignInIntent) -> Unit
) {

    val phoneNumber = remember { mutableStateOf("") }

    val signButtonEnabling = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        PhoneNumberField(
            mutableState = phoneNumber,
            onValueChange = { phoneNumber.value = it; signButtonEnabling.value = it.isNotEmpty() }
        ) {
            Text(text = "Enter Phone Number")
        }

        Row(
            modifier = Modifier
                .padding(24.dp)
                .height(48.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't you have an account?")
            ClickableText(
                text = AnnotatedString(
                    text = " LOGIN ",
                    paragraphStyle = ParagraphStyle(),
                    spanStyle = SpanStyle(color = Color.Blue)
                ),
                onClick = {

                }
            )
        }

        Button(
            onClick = {
                evenDispatcher.invoke(SignInIntent.SignIn(phoneNumber.value))
            },
            enabled = signButtonEnabling.value
        )
        {
            Text(text = "Sing Up!")
        }
    }
}

@Composable
fun PhoneNumberField(
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
