package cn.linhome.login.di

import cn.linhome.common.network.RetrofitManager
import cn.linhome.login.api.LoginApiService
import cn.linhome.login.ui.LoginRepository
import cn.linhome.login.vm.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  des : HomeKoin
 *  Created by 30Code
 *  date : 2021/7/28
 */
val moduleLogin = module {

    single { RetrofitManager.initRetrofit().getService(LoginApiService::class.java) }

    viewModel { LoginViewModel(get()) }

    single { LoginRepository(get()) }

}