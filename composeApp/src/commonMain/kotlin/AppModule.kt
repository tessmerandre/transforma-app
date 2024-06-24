import data.di.dataModule
import domain.di.domainModule
import org.koin.dsl.module

val appModule = module {
    includes(domainModule, dataModule, viewModelModule)
}