package cn.linhome.kotlinmvvmsamples.di

import cn.linhome.kotlinmvvmsamples.CoroutinesDispatcherProvider
import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.ApiService
import cn.linhome.kotlinmvvmsamples.model.repository.*
import cn.linhome.kotlinmvvmsamples.ui.home.vm.HomePageViewModel
import cn.linhome.kotlinmvvmsamples.ui.login.vm.LoginViewModel
import cn.linhome.kotlinmvvmsamples.ui.main.vm.SearchViewModel
import cn.linhome.kotlinmvvmsamples.ui.project.vm.ProjectLastedViewModel
import cn.linhome.kotlinmvvmsamples.ui.square.vm.SquareViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  des : KoinModule
 *  Created by 30Code
 *  date : 2021/1/11
 */

val viewModelModule = module {
    viewModel { LoginViewModel(get(),get()) }
    viewModel { HomePageViewModel(get(), get()) }
    viewModel { SquareViewModel(get()) }
    viewModel { ProjectLastedViewModel(get(), get()) }
//    viewModel { SystemViewModel(get(), get()) }
//    viewModel { NavigationViewModel(get()) }
//    viewModel { ProjectViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
//    viewModel { ShareViewModel(get()) }
}

val repositoryModule = module {
    single { RetrofitClient.getService(ApiService::class.java, ApiService.BASE_URL) }
    single { CoroutinesDispatcherProvider() }
    single { LoginRepository(get()) }
    single { SquareRepository() }
    single { HomePageRepository() }
    single { ProjectLastedRepository() }
    single { CollectRepository() }
//    single { SystemRepository() }
//    single { NavigationRepository() }
    single { SearchRepository() }
//    single { ShareRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)