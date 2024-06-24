import org.koin.dsl.module
import ui.feed.FeedViewModel
import ui.home.HomeViewModel

actual val viewModelModule = module {
    single { HomeViewModel() }
    single { FeedViewModel() }
}