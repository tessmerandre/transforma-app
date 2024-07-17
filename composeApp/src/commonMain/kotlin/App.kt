import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import ui.feed.FeedScreen
import ui.home.HomeScreen
import ui.navigation.SetupNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            SetupNavGraph(navController)
        }
    }
}



data object Routes {
    const val HOME = "home"
    const val FEED = "feed"
}