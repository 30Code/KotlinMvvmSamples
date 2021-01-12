package cn.linhome.kotlinmvvmsamples.di

import cn.linhome.kotlinmvvmsamples.CoroutinesDispatcherProvider
import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.ApiService
import cn.linhome.kotlinmvvmsamples.model.repository.LoginRepository
import cn.linhome.kotlinmvvmsamples.ui.login.vm.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  des : KoinModule
 *  Created by 30Code
 *  date : 2021/1/11
 */

val viewModelModule = module {
    viewModel { LoginViewModel(get(),get()) }
//    viewModel { ArticleViewModel(get(), get(), get(), get(), get()) }
//    viewModel { SystemViewModel(get(), get()) }
//    viewModel { NavigationViewModel(get()) }
//    viewModel { ProjectViewModel(get()) }
//    viewModel { SearchViewModel(get(), get()) }
//    viewModel { ShareViewModel(get()) }
}

val repositoryModule = module {
    single { RetrofitClient.getService(ApiService::class.java, ApiService.BASE_URL) }
    single { CoroutinesDispatcherProvider() }
    single { LoginRepository(get()) }
//    single { SquareRepository() }
//    single { HomeRepository() }
//    single { ProjectRepository() }
//    single { CollectRepository() }
//    single { SystemRepository() }
//    single { NavigationRepository() }
//    single { SearchRepository() }
//    single { ShareRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)