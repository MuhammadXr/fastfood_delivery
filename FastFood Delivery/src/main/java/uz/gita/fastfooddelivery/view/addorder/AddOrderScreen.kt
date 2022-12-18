package uz.gita.fastfooddelivery.view.addorder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderIntent
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderUiState
import uz.gita.fastfooddelivery.view.addorder.viewmodel.AddOrderViewModel
import uz.gita.fastfooddelivery.viewmodel.AddOrderViewModelImpl


class AddOrderScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val viewModel: AddOrderViewModel = getViewModel<AddOrderViewModelImpl>()
        val uiState = viewModel.collectAsState().value

        AddOrderScreenContent(uiState = uiState, eventDispatcher = viewModel::onEventDispatcher)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrderScreenContent(
    uiState: AddOrderUiState,
    eventDispatcher: (AddOrderIntent) -> Unit
) {
    val inputName = remember { mutableStateOf(TextFieldValue()) }
    val inputPrice = remember { mutableStateOf(TextFieldValue()) }
    val inputInfo = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        TextField(
            value = inputName.value,
            onValueChange = { inputName.value = it },
            singleLine = true
        )
        TextField(
            value = inputInfo.value,
            onValueChange = { inputInfo.value = it },
            singleLine = true

        )
        TextField(
            value = inputPrice.value,
            onValueChange = { inputPrice.value = it },
            singleLine = true

        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {

                val order = OrderData(
                    name = inputName.value.text,
                    price = inputPrice.value.text.toLong(),
                    info = inputInfo.value.text
                )
                eventDispatcher.invoke(AddOrderIntent.AddOrder(order))
            }
        ) {
            Text(text = "Add")
        }

    }

}