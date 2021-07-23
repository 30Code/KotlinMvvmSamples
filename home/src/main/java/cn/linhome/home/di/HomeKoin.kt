package cn.linhome.home.di

import cn.linhome.common.network.RetrofitManager
import cn.linhome.home.api.HomeApiService
import cn.linhome.home.ui.HomeArticlePagingAdapter
import cn.linhome.home.ui.HomeArticleRepository
import cn.linhome.home.ui.HomeFragment
import cn.linhome.home.vm.HomeArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  des : HomeKoin
 *  Created by 30Code
 *  date : 2021/7/23
 */
val moduleHome = module {

    single { RetrofitManager.initRetrofit().getService(HomeApiService::class.java) }

    viewModel { HomeArticleViewModel(get()) }

    single { HomeArticleRepository(get()) }

    scope<HomeFragment> {
        scoped { HomeArticlePagingAdapter() }
    }

}