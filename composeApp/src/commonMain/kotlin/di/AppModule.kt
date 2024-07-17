package di

import data.di.dataModule
import domain.di.domainModule
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ui.feed.FeedViewModel
import ui.home.HomeViewModel

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { FeedViewModel() }
    includes(domainModule, dataModule)
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}