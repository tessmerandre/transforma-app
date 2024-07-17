package ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.feed.FeedScreen
import ui.home.HomeScreen
import ui.login.LoginScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Login
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Login> {
            LoginScreen(
                goToFeed = {
                    navController.navigate(Screen.Detail(itemId = it.id))
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                goToFeed = {
                    navController.navigate(Screen.Detail(itemId = it.id))
                }
            )
        }
        composable<Screen.Detail> {
            FeedScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}