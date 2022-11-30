package uz.gita.fastfooddelivery.ui.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.core.data.models.OrderData
import uz.gita.fastfooddelivery.ui.main.viewmodel.Intent
import uz.gita.fastfooddelivery.ui.main.viewmodel.MainScreenViewModel
import uz.gita.fastfooddelivery.ui.main.viewmodel.UiState
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
    eventDispatcher: (Intent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(eventDispatcher)
        Log.d("TTT", " LIST SIZE ${uiState.orderList.size}")
        LazyColumn {
            items(
                count = uiState.orderList.size,
                itemContent = { index ->
                    val item = uiState.orderList[index]
                    OrderItemView(orderData = item)
                }
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        FloatingActionButton(
            onClick = { eventDispatcher.invoke(Intent.AddOrderButton) },
            modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)
        ) {

        }
    }
}


@Composable
fun TopAppBar(
    eventDispatcher: (Intent) -> Unit
) {
    Column {
        Row {
            IconButton(onClick = { eventDispatcher.invoke(Intent.Back) }) {
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