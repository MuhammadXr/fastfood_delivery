package uz.gita.fastfoodorder.view.main

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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.core.data.models.ProductData
import uz.gita.fastfoodorder.screen_items.CategoryScreenItem
import uz.gita.fastfoodorder.screen_items.ProductItem
import uz.gita.fastfoodorder.screen_items.ProductItemTitle
import uz.gita.fastfoodorder.view.main.viewmodel.MainIntent
import uz.gita.fastfoodorder.view.main.viewmodel.ProductsScreenViewModel
import uz.gita.fastfoodorder.view.main.viewmodel.UiState
import uz.gita.fastfoodorder.viewmodel.ProductsScreenViewModelImpl


class ProductsScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        val viewModel: ProductsScreenViewModel = getViewModel<ProductsScreenViewModelImpl>()
        viewModel.setNavigator(LocalNavigator.currentOrThrow)
        val uiState = viewModel.collectAsState().value
        ProductsScreenContent(
            uiState,
            viewModel::onEventDispatcher
        )

    }

}


@Composable
fun ProductsScreenContent(
    uiState: UiState,
    eventDispatcher: (MainIntent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        TopAppBar(eventDispatcher)
        val list = uiState.categoryItems
        LazyRow {
            items(
                count = list.size,
                itemContent = { index ->
                    val item = list[index]
                    CategoryScreenItem(
                        categoryData = item,
                        horizontalPadding = 4.dp,
                        verticalPadding = 4.dp
                    )
                }
            )
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 4.dp),
            columns = GridCells.Adaptive(minSize = 180.dp)
        ) {

            list.forEach { categoryItem ->
                val orderList = uiState.itemsList
                    .filter { orderItem -> (orderItem).category == categoryItem.name }


                if (orderList.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        ProductItemTitle(title = categoryItem.name, horizontalPadding = 8.dp, verticalPadding = 8.dp)
                    }

                    items(
                        count = orderList.size,
                        itemContent = { index ->
                            val item = orderList[index]
                            ProductItem(
                                productData = item,
                                horizontalPadding = 4.dp,
                                verticalPadding = 10.dp,
                                buttonAddToCart = {
                                    eventDispatcher.invoke(MainIntent.AddToCart)
                                }
                            )
                        }
                    )
                }
            }

        }
    }

//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(modifier = Modifier
//            .align(Alignment.BottomEnd)
//            .padding(10.dp)
//            .background(Color.Transparent)
//            .wrapContentHeight()
//            .padding(4.dp)) {
//
//            FloatingActionButton(
//                onClick = { eventDispatcher.invoke(MainIntent.AddOrderButton) },
//                modifier = Modifier
//                    .padding(10.dp)
//            ) {
//                Image(imageVector = Icons.Filled.Add, contentDescription = null)
//            }
//
//            FloatingActionButton(
//                onClick = { eventDispatcher.invoke(MainIntent.GotoAddCategory) },
//                modifier = Modifier
//                    .padding(10.dp)
//            ) {
//                Image(imageVector = Icons.Filled.AddTask, contentDescription = null)
//            }
//        }
//
//    }
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
    productData: ProductData
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = productData.name)
            Spacer(modifier = Modifier.weight(1f))
            GlideImage(
                imageModel = { productData.imgUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.height(55.dp)
            )
        }
    }
}