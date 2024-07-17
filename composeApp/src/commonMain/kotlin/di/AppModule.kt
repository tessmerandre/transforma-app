package di

import data.di.dataModule
import domain.di.domainModule
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ui.detail.DetailViewModel
import ui.home.HomeViewModel
import ui.login.LoginViewModel

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { HomeViewModel() }
    viewModel { DetailViewModel() }
    includes(domainModule, dataModule)
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}