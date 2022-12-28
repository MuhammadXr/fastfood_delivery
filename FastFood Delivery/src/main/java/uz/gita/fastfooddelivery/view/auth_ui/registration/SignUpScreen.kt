package uz.gita.fastfooddelivery.view.auth_ui.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen

@Preview
@Composable
fun Prev() {
    SignUpScreenContent()
}

class SignUpScreen : AndroidScreen() {

    @Composable
    override fun Content() {

    }

}

@Composable
fun SignUpScreenContent() {

    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val storeName = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }

    val signButtonEnabling = remember { mutableStateOf(true) }
    val waitResponseState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        PhoneNumberField(mutableState = phoneNumber, onValueChange = { phoneNumber.value = it })
        PasswordField(mutableState = password, onValueChange = { password.value = it })
        StoreNameField(mutableState = storeName, onValueChange = { storeName.value = it })
        LocationField(mutableState = location, onValueChange = { location.value = it })

        Row (modifier = Modifier.padding(24.dp).height(48.dp)){
            Text(text = "Do you want to login?")
            ClickableText(
                text = AnnotatedString(
                    text = "LOGIN",
                    paragraphStyle = ParagraphStyle()
                ),
                onClick = {

                }
            )
        }

        Button(
            onClick = {

            },
            enabled = signButtonEnabling.value
        )
        {
            if (waitResponseState.value) {
                Text(text = "Sing Up!")
            } else {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun PhoneNumberField(
    mutableState: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = mutableState.value,
        onValueChange = onValueChange
    )
}

@Composable
fun PasswordField(
    mutableState: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = mutableState.value,
        onValueChange = onValueChange
    )
}

@Composable
fun StoreNameField(
    mutableState: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = mutableState.value,
        onValueChange = onValueChange
    )
}

@Composable
fun LocationField(
    mutableState: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = mutableState.value,
        onValueChange = onValueChange
    )
}