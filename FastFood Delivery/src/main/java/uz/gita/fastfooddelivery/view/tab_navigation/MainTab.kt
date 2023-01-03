package uz.gita.fastfooddelivery.view.tab_navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.ScaleTransition
import uz.gita.fastfooddelivery.view.main.MainScreen

object MainTab: Tab {

    override val options: TabOptions
    @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Home)
            return TabOptions(
                index = 0u,
                title = "Main",
                icon = icon
            )
        }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {
        Navigator(screen = MainScreen()){ navigator ->
            ScaleTransition(navigator = navigator){ screen ->
                screen.Content()
            }
        }
    }
}