package uz.gita.fastfooddelivery.view.bottom_navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.transitions.ScaleTransition
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.fastfooddelivery.navigations.NavigationHandler
import uz.gita.fastfooddelivery.view.main.MainScreen

@SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tab.TabContent(

) {

    LifecycleEffect()


}