package uz.gita.fastfooddelivery.view.tab_navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.ScaleTransition
import uz.gita.fastfooddelivery.view.delivery.DeliveryScreen
import uz.gita.fastfooddelivery.view.orders.OrdersScreen
import uz.gita.fastfooddelivery.view.profile.ProfileScreen

object DeliveryTab:Tab {
    override val options: TabOptions
    @Composable
        get(){
        val icon = rememberVectorPainter(image = Icons.Default.LocalShipping)
        return TabOptions(
            index = 0u,
            title = "Delivery",
            icon = icon
        )
        }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {
        Navigator(screen = DeliveryScreen()){ navigator ->
            ScaleTransition(navigator = navigator){ screen ->
                screen.Content()
            }
        }
    }
}