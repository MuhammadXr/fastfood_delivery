package uz.gita.fastfooddelivery.screen_items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.fastfooddelivery.R

@Preview
@Composable
fun TestP(){
    BigOrderItem()
}

@Composable
fun BigOrderItem(){
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .height(140.dp)
            .width(250.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.burger_big),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    )
                ))
                .padding(8.dp)

        ) {
            Text(
                text = "Title",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily.Serif,
            )
            Text(
                text = "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.",
                fontSize = 10.sp,
                color = Color.White,
                fontFamily = FontFamily.Serif,
            )
        }


    }
}