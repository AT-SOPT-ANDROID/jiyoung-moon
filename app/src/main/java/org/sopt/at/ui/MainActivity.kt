package org.sopt.at.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
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
import org.sopt.at.ui.mypage.screen.MyScreen
import org.sopt.at.ui.onboarding.screen.SignInScreen
import org.sopt.at.ui.onboarding.screen.SignUpScreen
import org.sopt.at.ui.search.screen.SearchScreen
import org.sopt.at.ui.shorts.screen.ShortsScreen
import org.sopt.at.ui.theme.TvingTheme

class MainActivity : ComponentActivity() {
    private val contentViewModel: ContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry =
                navController.currentBackStackEntryFlow.collectAsState(initial = navController.currentBackStackEntry)
            val currentRoute = currentBackStackEntry.value?.destination?.route  // 현재 화면
            val showBottomNavBar = when (currentRoute) {
                NavRoutes.SignIn.route, NavRoutes.SignUp.route -> false
                else -> true
            }

            TvingTheme {
                Scaffold(
                    bottomBar = {
                        if (showBottomNavBar) {
                            BottomNavBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.SignIn.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavRoutes.SignIn.route) {
                            SignInScreen(navController = navController)
                        }
                        composable(NavRoutes.SignUp.route) {
                            SignUpScreen(navController = navController)
                        }

                        composable(NavRoutes.Mypage.route) {
                            MyScreen(navController = navController)
                        }

                        composable(NavRoutes.Home.route) {
                            HomeScreen(
                                viewModel = contentViewModel,
                                navController = navController
                            )
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