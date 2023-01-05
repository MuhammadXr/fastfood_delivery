package uz.gita.fastfoodorder.view.tab_navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.ScaleTransition
import uz.gita.fastfoodorder.view.profile.ProfileScreen

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

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {

    }
}