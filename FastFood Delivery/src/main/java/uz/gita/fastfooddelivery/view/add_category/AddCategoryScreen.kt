package uz.gita.fastfooddelivery.view.add_category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.fastfooddelivery.view.add_category.viewmodel.A_C_UiState
import uz.gita.fastfooddelivery.view.add_category.viewmodel.AddCategoryIntent
import uz.gita.fastfooddelivery.view.add_category.viewmodel.AddCategoryViewModel
import uz.gita.fastfooddelivery.viewmodel.AddCategoryViewModelImpl

class AddCategoryScreen: AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel : AddCategoryViewModel = getViewModel<AddCategoryViewModelImpl>()
        val uiState = viewModel.collectAsState().value

        AddCategoryScreenContent(
            uiState = uiState,
            eventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun AddCategoryScreenContent(
    uiState: A_C_UiState,
    eventDispatcher: (AddCategoryIntent) -> Unit
){
    val nameState = remember { mutableStateOf("") }
    val buttonEnabledState = remember { mutableStateOf(uiState.state) }

    Box(modifier = Modifier.fillMaxSize()){
        IconButton(onClick = { eventDispatcher.invoke(AddCategoryIntent.Back) }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null )
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){

        TextField(value = nameState.value, onValueChange = {
            nameState.value = it
            buttonEnabledState.value = it.isNotEmpty()
        })

        Button(
            onClick = { eventDispatcher.invoke(AddCategoryIntent.Add(nameState.value)) },
            enabled = buttonEnabledState.value
        )
        {
            Text(text = "ADD")
        }

    }
}