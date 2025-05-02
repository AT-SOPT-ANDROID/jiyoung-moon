package org.sopt.at.ui.common.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Shorts : NavRoutes("shorts")
    object Live : NavRoutes("live")
    object Search : NavRoutes("search")
    object History : NavRoutes("history")
}