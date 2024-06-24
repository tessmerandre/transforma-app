import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.feed.FeedScreen
import ui.home.HomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) {
                HomeScreen(
                    goToFeed = {
                        navController.navigate(Routes.FEED)
                    }
                )
            }
            composable(Routes.FEED) {
                FeedScreen(
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

data object Routes {
    const val HOME = "home"
    const val FEED = "feed"
}