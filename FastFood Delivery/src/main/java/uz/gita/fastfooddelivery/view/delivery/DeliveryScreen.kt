package uz.gita.fastfooddelivery.view.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.fastfooddelivery.screen_items.DeliveryItem
import uz.gita.fastfooddelivery.screen_items.OrderItem
import uz.gita.fastfooddelivery.view.delivery.viewmodel.DeliveryIntent
import uz.gita.fastfooddelivery.view.delivery.viewmodel.DeliveryUiState

class DeliveryScreen : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun DeliveryScreenContent(
    uiState: DeliveryUiState,
    eventDispatcher :(DeliveryIntent) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        TopAppBar(eventDispatcher)

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Adaptive(minSize = 180.dp)
        ) {

            items(
                count = uiState.deliveryList.size,
                itemContent = { index ->
                    val item = uiState.deliveryList[index]
                    DeliveryItem(
                        deliveryData = item,
                        horizontalPadding = 4.dp,
                        verticalPadding = 10.dp,
                        buttonAddToCart = {

                        }
                    )
                }
            )

        }
    }
}

@Composable
fun TopAppBar(
    eventDispatcher: (DeliveryIntent) -> Unit
) {
    Column {
        Row {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backButton")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}