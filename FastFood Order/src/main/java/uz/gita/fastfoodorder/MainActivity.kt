package uz.gita.fastfoodorder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.fastfoodorder.navigations.NavigationHandler
import uz.gita.fastfoodorder.view.splash.SplashScreen
import uz.gita.fastfoodorder.view.tab_navigation.*
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
            Navigator(screen = SplashScreen()){ navigator ->
                navigationHandler.navigationStack
                    .onEach { it.invoke(navigator) }
                    .launchIn(lifecycleScope)
                FadeTransition(navigator = navigator)
            }
        }
    }
}

