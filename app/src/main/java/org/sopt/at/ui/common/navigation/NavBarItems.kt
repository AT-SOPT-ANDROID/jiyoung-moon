package org.sopt.at.ui.common.navigation

import org.sopt.at.R

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            imageId = R.drawable.ic_home,
            route = "home"
        ),
        BarItem(
            title = "Shorts",
            imageId = R.drawable.ic_shorts,
            route = "shorts"
        ),
        BarItem(
            title = "Live",
            imageId = R.drawable.ic_live,
            route = "live"
        ),
        BarItem(
            title = "Search",
            imageId = R.drawable.ic_search,
            route = "search"
        ),
        BarItem(
            title = "History",
            imageId = R.drawable.ic_history,
            route = "history"
        )
    )
}