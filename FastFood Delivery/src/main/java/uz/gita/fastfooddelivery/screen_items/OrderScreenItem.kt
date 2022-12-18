package uz.gita.fastfooddelivery.screen_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
fun PreScreen(){
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
){
    Column(
        modifier = Modifier
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .width(140.dp)
            .height(200.dp)
            .background(color = Color.Gray, RoundedCornerShape(8.dp))
            .padding(4.dp)
//        modifier = Modifier
//            .clip(shape = )
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(0xFFC6A3DF), shape = RoundedCornerShape(10.dp))
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
//            Image(
//                painter = painterResource(id = orderData.),
//                contentDescription = "Item Photo",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)
//            )
            GlideImage(
                imageModel = { orderData.imgUrl }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            
            Text(
                text = orderData.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = orderData.price.toString(),
                fontSize = 22.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = buttonAddToCart,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "To Cart", fontSize = 12.sp)
            }

        }

    }
}


