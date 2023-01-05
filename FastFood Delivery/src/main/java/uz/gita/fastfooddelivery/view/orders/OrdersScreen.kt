package uz.gita.fastfooddelivery.view.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.fastfooddelivery.screen_items.CategoryScreenItem
import uz.gita.fastfooddelivery.screen_items.OrderItem
import uz.gita.fastfooddelivery.screen_items.ProductItem
import uz.gita.fastfooddelivery.view.main.viewmodel.MainIntent
import uz.gita.fastfooddelivery.view.orders.viewmodel.OrdersIntent
import uz.gita.fastfooddelivery.view.orders.viewmodel.OrdersUiState
import uz.gita.fastfooddelivery.view.orders.viewmodel.OrdersViewModel
import uz.gita.fastfooddelivery.viewmodel.OrdersViewModelImpl

class OrdersScreen: AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: OrdersViewModel = getViewModel<OrdersViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        OrdersScreenContent(uiState = uiState, eventDispatcher = viewModel::onEventDispatcher)

    }
}

@Composable
fun OrdersScreenContent(
    uiState: OrdersUiState,
    eventDispatcher: (OrdersIntent) -> Unit
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        TopAppBar(eventDispatcher)

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Adaptive(minSize = 180.dp)
        ) {

            items(
                count = uiState.ordersList.size,
                itemContent = { index ->
                    val item = uiState.ordersList[index]
                    OrderItem(
                        orderData = item,
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
    eventDispatcher: (OrdersIntent) -> Unit
) {
    Column {
        Row {
            IconButton(onClick = { eventDispatcher.invoke(OrdersIntent.Back) }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backButton")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
