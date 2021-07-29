package cn.linhome.home.di

import cn.linhome.common.network.RetrofitManager
import cn.linhome.home.adapter.HomeMultiArticlePagingAdapter
import cn.linhome.home.api.HomeApiService
import cn.linhome.home.ui.coin.CoinRepository
import cn.linhome.home.ui.home.HomeArticleRepository
import cn.linhome.home.ui.home.HomeFragment
import cn.linhome.home.vm.CoinViewModel
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
    viewModel { CoinViewModel(get()) }

    single { HomeArticleRepository(get()) }
    single { CoinRepository(get()) }

    scope<HomeFragment> {
        scoped {
//            HomeArticlePagingAdapter()
            HomeMultiArticlePagingAdapter()
        }
    }

}