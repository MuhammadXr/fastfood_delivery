package uz.gita.fastfooddelivery.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.screen_items.CategoryScreenItem
import uz.gita.fastfooddelivery.screen_items.OrderItem
import uz.gita.fastfooddelivery.view.main.viewmodel.MainIntent
import uz.gita.fastfooddelivery.view.main.viewmodel.MainScreenViewModel
import uz.gita.fastfooddelivery.view.main.viewmodel.UiState
import uz.gita.fastfooddelivery.viewmodel.MainScreenViewModelImpl


class MainScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val viewModel: MainScreenViewModel = getViewModel<MainScreenViewModelImpl>()
        val uiState = viewModel.collectAsState().value

        MainScreenContent(
            uiState,
            viewModel::onEventDispatcher
        )

    }

}


@Composable
fun MainScreenContent(
    uiState: UiState,
    eventDispatcher: (MainIntent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(eventDispatcher)

        LazyVerticalGrid(
            modifier = Modifier.padding(10.dp),
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            item (span = { GridItemSpan(2) }){
                LazyRow {
                    items(
                        count = uiState.categoryList.size,
                        itemContent = { index ->
                            val item = uiState.categoryList[index]
                            CategoryScreenItem(
                                categoryData = item,
                                horizontalPadding = 4.dp,
                                verticalPadding = 4.dp
                            )
                        }
                    )
                }
            }


            items(
                count = uiState.orderList.size,
                itemContent = { index ->
                    val item = uiState.orderList[index]
                    OrderItem(
                        orderData = item,
                        horizontalPadding = 10.dp,
                        verticalPadding = 10.dp,
                        buttonAddToCart = {
                            eventDispatcher.invoke(MainIntent.AddToCart)
                        }
                    )
                }
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(20.dp)
            .background(Color.Blue)
            .wrapContentHeight()
            .padding(4.dp)) {

            FloatingActionButton(
                onClick = { eventDispatcher.invoke(MainIntent.AddOrderButton) },
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Image(imageVector = Icons.Filled.Add, contentDescription = null)
            }

            FloatingActionButton(
                onClick = { eventDispatcher.invoke(MainIntent.GotoAddCategory) },
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Image(imageVector = Icons.Filled.AddTask, contentDescription = null)
            }
        }

    }
}


@Composable
fun TopAppBar(
    eventDispatcher: (MainIntent) -> Unit
) {
    Column {
        Row {
            IconButton(onClick = { eventDispatcher.invoke(MainIntent.Back) }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backButton")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun OrderItemView(
    orderData: OrderData
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = orderData.name)
            Spacer(modifier = Modifier.weight(1f))
            GlideImage(
                imageModel = { orderData.imgUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.height(55.dp)
            )
        }
    }
}