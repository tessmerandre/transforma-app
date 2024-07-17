package ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ui.feed.DetailScreen
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
                goToHome = {
                    navController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                onItemClick = {
                    navController.navigate(Screen.Detail(itemId = it.id))
                }
            )
        }
        composable<Screen.Detail> {
            val route = it.toRoute<Screen.Detail>()

            DetailScreen(
                itemId = route.itemId, // move to ViewModel savedStateHandle
                onBackPressed = navController::popBackStack
            )
        }
    }
}