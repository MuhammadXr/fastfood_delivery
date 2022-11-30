package uz.gita.fastfoodorder.navigations

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.Flow

typealias NavArgs = Navigator.() -> Unit

interface NavigationHandler {
    val navigationStack : Flow<NavArgs>
}