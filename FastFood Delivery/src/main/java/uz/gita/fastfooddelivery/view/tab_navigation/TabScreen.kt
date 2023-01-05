package uz.gita.fastfooddelivery.view.tab_navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

class TabScreen: AndroidScreen() {
    @Composable
    override fun Content() {
        TabScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabScreenContent() {
    TabNavigator(tab = MainTab) { tabNavigator ->
        Scaffold(
            topBar = { },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    CurrentTab()
                }
            },
            bottomBar = {
                BottomNavigation {
                    TabNavigationItem(tab = OrdersTab)
                    TabNavigationItem(tab = DeliveryTab)
                    TabNavigationItem(tab = MainTab)
                    TabNavigationItem(tab = ProfileTab)
                }
            }
        )
    }
}

@Composable
fun SimpleMainScreen() {
//    Navigator(
//        screen = MainScreen(),
//    ) { navigator ->
//        navigationHandler.navigationStack
//            .onEach { it.invoke(navigator) }
//            .launchIn(lifecycleScope)
//        SlideTransition(navigator = navigator)
//    }
}

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {

    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(painter = tab.options.icon!!, contentDescription = tab.options.title)
                Text(text = tab.options.title)
            }

        }
    )

}