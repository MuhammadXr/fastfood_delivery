package uz.gita.fastfooddelivery.navigations

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : AppNavigation, NavigationHandler {

    override val navigationStack = MutableSharedFlow<NavArgs>()

    private suspend fun navigate(navArgs: NavArgs) {
        navigationStack.emit(navArgs)
    }

    override suspend fun back() = navigate { pop() }

    override suspend fun backAll() = navigate{ popAll() }

    override suspend fun backToRoot() = navigate { popUntilRoot() }

    override suspend fun navigateTo(screen: AppScreen) = navigate {
        push(screen)
    }


}