package uz.gita.fastfooddelivery.screen_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.core.data.models.CategoryData

@Composable
fun CategoryScreenItem(
    categoryData: CategoryData,
    verticalPadding: Dp,
    horizontalPadding: Dp,

){
    Column(
        modifier = Modifier
            .height(30.dp)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .background(
                Color(0x663ADDff),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 2.dp)
        ,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Text(
            text = categoryData.name,
            modifier = Modifier.padding(1.dp),
            fontSize = 12.sp
        )
    }
}