package ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Login
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToDetails = {
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(
                navigateToHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}