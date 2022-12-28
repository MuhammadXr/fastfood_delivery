package uz.gita.fastfoodorder.screen_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import uz.gita.core.data.models.OrderData


@Preview
@Composable
fun PreScreen() {
    Column {
        //OrderItem()
        //CategoryScreenItem()
    }

}


@Composable
fun OrderItem(
    orderData: OrderData,
    buttonAddToCart: () -> Unit,
    horizontalPadding: Dp,
    verticalPadding: Dp,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .width(140.dp)
            .height(210.dp)
            .background(color = Color.White, RoundedCornerShape(8.dp))
            .padding(2.dp)


//        modifier = Modifier
//            .clip(shape = )
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFFC6A3DF), shape = RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            GlideImage(
                imageModel = { orderData.imgUrl }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

        }
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = orderData.name,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = orderData.price.toString(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(
                onClick = buttonAddToCart,
            ) {
                Text(text = "To Cart", fontSize = 12.sp)
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

        }

    }
}

@Composable
fun OrderItemTitle(
    title: String,
    horizontalPadding: Dp,
    verticalPadding: Dp,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(text = title, fontSize = 24.sp)
    }
}

