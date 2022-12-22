package uz.gita.fastfooddelivery.view.addorder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
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
        viewModel.setNavigator(LocalNavigator.currentOrThrow)
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
    val selectCategory = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = { eventDispatcher.invoke(AddOrderIntent.Back) }) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = null
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(text = "Order Adding...", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        TextField(
            value = inputName.value,
            onValueChange = { inputName.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Name") }
        )
        TextField(
            value = inputInfo.value,
            onValueChange = { inputInfo.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Description") }
        )
        TextField(
            value = inputPrice.value,
            onValueChange = { inputPrice.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            placeholder = { Text(text = "Price") }
        )

        DropDownSpinner(
            selectedItem = selectCategory.value,
            onItemSelected = { _, item ->
                selectCategory.value = item as String
            },
            itemList = uiState.categoriesList.map { it.name },
            defaultText = "Select Category",

            )

        Button(
            onClick = {

                val order = OrderData(
                    name = inputName.value.text,
                    price = inputPrice.value.text.toLong(),
                    info = inputInfo.value.text,
                    category = selectCategory.value
                )
                eventDispatcher.invoke(AddOrderIntent.AddOrder(order))
            }
        ) {
            Text(text = "Add")
        }

    }

}

@Composable
fun <E> DropDownSpinner(
    modifier: Modifier = Modifier
        .height(48.dp)
        .width(280.dp),
    defaultText: String = "Select...",
    selectedItem: E,
    onItemSelected: (Int, E) -> Unit,
    itemList: List<E>?
) {
    val isOpen = remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (selectedItem == null || selectedItem.toString().isEmpty()) {
            Text(
                text = defaultText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 3.dp),
                color = MaterialTheme.colors.onSurface.copy(.45f)
            )
        }

        Text(
            text = selectedItem?.toString() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 32.dp, bottom = 3.dp),
            color = MaterialTheme.colors.onSurface
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(.85f),
            expanded = isOpen.value,
            onDismissRequest = {
                isOpen.value = false
            },
        ) {
            itemList?.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        isOpen.value = false
                        onItemSelected(index, item)
                    },
                    text = {
                        Text(item.toString())
                    }
                )
            }
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(24.dp),
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown"
        )

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}