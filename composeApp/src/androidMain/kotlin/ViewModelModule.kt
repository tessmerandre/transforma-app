
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ui.feed.FeedViewModel
import ui.home.HomeViewModel

actual val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { FeedViewModel() }
}