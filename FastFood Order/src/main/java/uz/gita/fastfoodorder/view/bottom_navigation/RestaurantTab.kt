package uz.gita.fastfoodorder.view.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object RestaurantTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Storefront)
            return TabOptions(
                index = 0u,
                title = "Restaurants",
                icon = icon
            )
        }

    @Composable
    override fun Content() {

    }
}