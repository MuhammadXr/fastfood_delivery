package uz.gita.fastfooddelivery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.fastfooddelivery.navigations.NavigationHandler
import uz.gita.fastfooddelivery.view.main.MainScreen
import uz.gita.fastfooddelivery.view.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Navigator(
                    screen = MainScreen(),
                ) { navigator ->
                    navigationHandler.navigationStack
                        .onEach { it.invoke(navigator) }
                        .launchIn(lifecycleScope)
                    SlideTransition(navigator = navigator)
                }
            }
        }
    }
}
