package org.sopt.at.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.common.navigation.BottomNavBar
import org.sopt.at.ui.common.navigation.NavRoutes
import org.sopt.at.ui.history.screen.HistoryScreen
import org.sopt.at.ui.home.screen.HomeScreen
import org.sopt.at.ui.home.viewmodel.ContentViewModel
import org.sopt.at.ui.live.screen.LiveScreen
import org.sopt.at.ui.search.screen.SearchScreen
import org.sopt.at.ui.shorts.screen.ShortsScreen
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class MainActivity : ComponentActivity() {
    private val contentViewModel: ContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ATSOPTANDROIDTheme {
                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavRoutes.Home.route) {
                            HomeScreen(contentViewModel)
                        }
                        composable(NavRoutes.Shorts.route) {
                            ShortsScreen()
                        }
                        composable(NavRoutes.Live.route) {
                            LiveScreen()
                        }
                        composable(NavRoutes.Search.route) {
                            SearchScreen()
                        }
                        composable(NavRoutes.History.route) {
                            HistoryScreen()
                        }
                    }
                }
            }
        }
    }
}